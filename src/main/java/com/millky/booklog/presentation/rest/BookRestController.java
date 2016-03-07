package com.millky.booklog.presentation.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.millky.booklog.domain.model.entity.Book;
import com.millky.booklog.domain.model.repository.BookRepository;
import com.millky.booklog.domain.model.repository.LibraryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class BookRestController {

	@Value("${app.impl.book}")
	private String bookRepositoryId;

	@Resource(name = "${app.impl.book}")
	LibraryRepository libraryRepository;

	@Autowired
	BookRepository bookRepository;

	@RequestMapping(value = "/books/new", method = RequestMethod.GET)
	public List<Book> getNewBooks(@RequestParam(value = "q", required = true) String query) {
		System.out.println(bookRepositoryId);
		log.info("query = {}", query);
		return libraryRepository.findBooksFromLibrary(query);
	}

	// @RequestMapping(value = "/books", method = RequestMethod.POST)
	// @ResponseStatus(HttpStatus.CREATED)
	// public Book addBook(@Valid Book book) {
	// return bookRepository.addBook(book);
	// }

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Book addBook(String isbn) {
		log.info("isbn = {}", isbn);
		Book book = libraryRepository.findBookByIsbn(isbn);
		log.info("book = {}", book);
		return bookRepository.addBook(book);
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<Book> findBook() {
		return bookRepository.getBooks();
	}

}
