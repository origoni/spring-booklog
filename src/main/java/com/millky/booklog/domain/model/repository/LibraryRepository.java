package com.millky.booklog.domain.model.repository;

import java.util.List;

import com.millky.booklog.domain.model.entity.Book;

public interface LibraryRepository {

	public List<Book> findBooksFromLibrary(String q);

	public Book findBookByIsbn(String isbn);
}
