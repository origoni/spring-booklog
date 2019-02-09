package com.millky.booklog.domain.repository.impl;

import com.millky.booklog.domain.model.dto.DaumBook;
import com.millky.booklog.domain.model.entity.Book;
import com.millky.booklog.domain.repository.LibraryRepository;
import com.millky.booklog.infrastructure.client.DaumBookApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Repository("kakaoLibraryRepository")
public class DaumLibraryRepository implements LibraryRepository {

    @Autowired
    DaumBookApiClient daumBookApiClient;

    Function<DaumBook.Document, Book> externalToMyBook = t -> {
        Book book = new Book();

        try {
            if (t.getIsbn().length() < 13) {
                return null;
            }

            // https://developers.kakao.com/docs/restapi/search#책-검색
            // isbn	국제 표준 도서번호(ISBN10 ISBN13) (ISBN10,ISBN13 중 하나 이상 존재하며, ' '(공백)을 구분자로 출력됩니다) ex: "8996991341 9788996991342"
            // " 9788996991342" <- 이런식으로 오는 경우가 있음
            if (t.getIsbn().length() > 15 && t.getIsbn().contains(" ")) {
                book.setIsbn(Long.parseLong(t.getIsbn().substring(11)));
            } else {
                book.setIsbn(Long.parseLong(t.getIsbn().trim()));
            }

            book.setTitle(t.getTitle());
            book.setAuthors(String.join(", ", t.getAuthors()));
            book.setPublisher(t.getPublisher());
            book.setPublishedDate(t.getDatetime());

            book.setImageUrl(t.getThumbnail());

        } catch (Exception e) {
            return null;
        }

        log.info("book = {}", book);

        return book;
    };

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

}
