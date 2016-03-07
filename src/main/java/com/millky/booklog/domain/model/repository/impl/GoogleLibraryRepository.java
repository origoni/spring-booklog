package com.millky.booklog.domain.model.repository.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.millky.booklog.domain.model.dto.GoogleBook;
import com.millky.booklog.domain.model.entity.Book;
import com.millky.booklog.domain.model.repository.LibraryRepository;
import com.millky.booklog.infrastructure.client.GoogleBookApiClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class GoogleLibraryRepository implements LibraryRepository {

	@Autowired
	GoogleBookApiClient googleBookApiClient;

	@Override
	public List<Book> findBooksFromLibrary(String q) {
		return googleBookApiClient.getBooks(q).stream().map(externalToMyBook).filter(b -> b != null)
				.collect(Collectors.toList());
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		return googleBookApiClient.getBooks(isbn).stream().map(externalToMyBook).filter(b -> b != null).findFirst()
				.get(); // 개선 필요함. isbn이 제목일 수도 있음 ㅇㅇ
	}

	Function<GoogleBook.Item, Book> externalToMyBook = new Function<GoogleBook.Item, Book>() {

		public Book apply(GoogleBook.Item t) {

			log.info("t.getVolumeInfo() = {}", t.getVolumeInfo());

			Book book = new Book();
			try {
				book.setTitle(t.getVolumeInfo().getTitle());
				book.setAuthors(t.getVolumeInfo().getAuthors().stream().collect(Collectors.joining(", ")));
				book.setPublisher(t.getVolumeInfo().getPublisher());
				book.setPublishedDate(t.getVolumeInfo().getPublishedDate());
				book.setIsbn(Long.parseLong(t.getVolumeInfo().getIndustryIdentifiers().stream()
						.filter(i -> i.getType().equals("ISBN_13")).findFirst().get().getIdentifier()));
				if (book.getIsbn() == 0) {
					throw new RuntimeException();
				}
				book.setImageUrl(t.getVolumeInfo().getImageLinks().getThumbnail());
			} catch (Exception e) {
				return null;
			}

			return book;
		}
	};

}
