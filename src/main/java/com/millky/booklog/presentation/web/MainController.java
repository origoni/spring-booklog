package com.millky.booklog.presentation.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // ①
public class MainController {

	@RequestMapping("/") // ②
	public String viewMain(Model model) { // ③
		model.addAttribute("title", "On Spring BookLog");
		model.addAttribute("message", "안녕, World!"); // ④
		model.addAttribute("date", new Date());// ④
		return "main"; // ⑤
	}
}
