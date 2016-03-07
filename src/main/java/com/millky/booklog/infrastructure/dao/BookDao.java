package com.millky.booklog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.millky.booklog.domain.model.entity.Book;

public interface BookDao extends JpaRepository<Book, Long> {

}
