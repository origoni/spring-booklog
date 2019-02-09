package com.millky.booklog.presentation.rest;

import java.util.List;
import java.util.Map;

import com.millky.booklog.presentation.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.millky.booklog.domain.model.entity.Book;
import com.millky.booklog.domain.repository.BookRepository;
import com.millky.booklog.domain.repository.LibraryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class BookRestController {

    @Autowired
    Map<String, LibraryRepository> libraryRepositoryMap;

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/books/new", method = RequestMethod.GET)
    public List<Book> getNewBooks(
            @RequestParam(required = false, defaultValue = "kakao") Provider p,
            @RequestParam(value = "q", required = true) String query
    ) {
        log.info("Provider = {}; query = {}", p, query);

        LibraryRepository libraryRepository = libraryRepositoryMap.get(p.name() + "LibraryRepository");

        return libraryRepository.findBooksFromLibrary(query);
    }

    // @RequestMapping(value = "/books", method = RequestMethod.POST)
    // @ResponseStatus(HttpStatus.CREATED)
    // public Book addBook(@Valid Book book) {
    // return bookRepository.addBook(book);
    // }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestParam(required = false) Provider p,
                        String isbn) {
        log.info("Provider = {}; isbn = {}", p, isbn);

        LibraryRepository libraryRepository = libraryRepositoryMap.get(p.name() + "LibraryRepository");


        Book book = libraryRepository.findBookByIsbn(isbn);
        log.info("book = {}", book);
        return bookRepository.addBook(book);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> findBook() {
        return bookRepository.getBooks();
    }

}
