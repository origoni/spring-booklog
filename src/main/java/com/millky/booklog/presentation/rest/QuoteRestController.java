package com.millky.booklog.presentation.rest;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.millky.booklog.domain.model.entity.Quote;
import com.millky.booklog.infrastructure.dao.QuoteDao;

@RestController
public class QuoteRestController {

	@Autowired
	QuoteDao quoteDao;

	@RequestMapping(value = "/quotes", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Quote list(@Valid Quote quote) {
		quote.setRecordAt(new Date());
		return quoteDao.save(quote);
	}
}
