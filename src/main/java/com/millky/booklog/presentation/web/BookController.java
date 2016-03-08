package com.millky.booklog.presentation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.millky.booklog.domain.model.entity.Book;
import com.millky.booklog.domain.repository.BookRepository;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Book book) {
		return "book/search";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		model.addAttribute("bookList", bookRepository.getBooks());
		return "book/list";
	}
}
