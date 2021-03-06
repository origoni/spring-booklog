package com.millky.booklog.domain.repository;

import com.millky.booklog.domain.model.entity.Book;
import com.millky.booklog.infrastructure.dao.BookDao;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookRepository {

    @NonNull
    private final BookDao bookDao;

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
