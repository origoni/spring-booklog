package com.millky.booklog.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.millky.booklog.domain.model.entity.Book;
import com.millky.booklog.infrastructure.dao.BookDao;

@Repository
public class BookRepository {

	@Autowired
	BookDao bookDao;

	public List<Book> getBooks() {
		return bookDao.findAll();
	}

	public Book addBook(Book book) {
		return bookDao.save(book);
	}

	public void delBook(Book book) {
		bookDao.delete(book);
	}
}
