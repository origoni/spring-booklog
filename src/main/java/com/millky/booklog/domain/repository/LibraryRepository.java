package com.millky.booklog.domain.repository;

import com.millky.booklog.domain.model.entity.Book;

import java.util.List;

public interface LibraryRepository {

    List<Book> findBooksFromLibrary(String q);

    Book findBookByIsbn(String isbn);
}
