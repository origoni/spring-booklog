package com.millky.booklog.presentation.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.millky.booklog.domain.model.entity.Quote;
import com.millky.booklog.domain.repository.BookRepository;
import com.millky.booklog.infrastructure.dao.QuoteDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/quote") // ①
public class QuoteController {
	@Autowired
	private QuoteDao quoteDao;

	@Autowired
	BookRepository bookRepository;

	@RequestMapping(value = "/write", method = RequestMethod.GET) // ②
	public String form(Model model) {
		model.addAttribute("bookList", bookRepository.getBooks());
		return "quote/form";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST) // ③
	public String write(Quote quote) {
		quote.setRecordAt(new Date());
		log.info("quote = {}", quote);
		return "redirect:/quote/" + quoteDao.save(quote).getId(); // ④
	}

	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable int id) {
		log.info("id = {}", id);
		Quote quote = quoteDao.findOne(id); // ⑤
		log.info("quote = {}", quote);
		model.addAttribute("quote", quote);
		return "quote/quote";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		List<Quote> quoteList = quoteDao.findAll(); // ⑥
		model.addAttribute("quoteList", quoteList);
		return "quote/list";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editor(Model model, @PathVariable int id) {
		Quote quote = quoteDao.findOne(id);
		model.addAttribute("bookList", bookRepository.getBooks());
		model.addAttribute("quote", quote);
		return "quote/form"; // ③
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String edit(Quote quote) {
		return "redirect:/quote/" + quoteDao.save(quote).getId();
	}

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable int id) {
		quoteDao.delete(id);
		return "redirect:/quote/list";
	}
}
