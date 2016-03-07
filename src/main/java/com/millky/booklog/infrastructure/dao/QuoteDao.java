package com.millky.booklog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.millky.booklog.domain.model.entity.Quote;

public interface QuoteDao extends JpaRepository<Quote, Integer> {

}
