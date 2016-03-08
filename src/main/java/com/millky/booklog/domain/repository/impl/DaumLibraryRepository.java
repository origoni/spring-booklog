package com.millky.booklog.domain.repository.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.millky.booklog.domain.repository.LibraryRepository;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.millky.booklog.domain.model.dto.DaumBook;
import com.millky.booklog.domain.model.entity.Book;
import com.millky.booklog.infrastructure.client.DaumBookApiClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DaumLibraryRepository implements LibraryRepository {

	@Autowired
	DaumBookApiClient daumBookApiClient;

	@Override
	public List<Book> findBooksFromLibrary(String q) {
		return daumBookApiClient.getDaumBooks(q, false).stream().map(externalToMyBook).filter(b -> b != null)
				.collect(Collectors.toList());
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		return daumBookApiClient.getDaumBooks(isbn, true).stream().map(externalToMyBook).filter(b -> b != null)
				.findFirst().get();
	}

	Function<DaumBook.Item, Book> externalToMyBook = new Function<DaumBook.Item, Book>() {

		public Book apply(DaumBook.Item t) {
			Book book = new Book();

			try {
				book.setTitle(removeTag(t.getTitle()));
				book.setAuthors(removeTag(t.getAuthor_t()));
				book.setPublisher(removeTag(t.getPub_nm()));
				log.info("t.getPub_date() = {}", t.getPub_date());
				book.setPublishedDate(t.getPub_date());
				book.setIsbn(Long.parseLong(removeTag(t.getIsbn13())));
				book.setImageUrl(t.getCover_l_url());

			} catch (Exception e) {
				return null;
			}

			log.info(t.getAuthor_t());

			return book;
		}
	};

	String removeTag(String html) {
		return Jsoup.clean(Jsoup.parse(html).text(), Whitelist.none()).trim();
	}

}
