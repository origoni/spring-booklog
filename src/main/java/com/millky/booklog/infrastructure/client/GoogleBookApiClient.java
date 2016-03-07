package com.millky.booklog.infrastructure.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.millky.booklog.domain.model.dto.GoogleBook;
import com.millky.booklog.domain.model.dto.GoogleBook.Item;

@Component
public class GoogleBookApiClient {

	@Value("${app.key.google}")
	private String GOOGLE_SEARCH_APIKEY;

	public List<Item> getBooks(String q) {

		RestTemplate restTemplate = new RestTemplate();

		String requestUrl = "https://www.googleapis.com/books/v1/volumes";
		requestUrl += "?key=" + GOOGLE_SEARCH_APIKEY;
		requestUrl += "&printType=books";
		requestUrl += "&maxResults=30";
		requestUrl += "&q=" + q; // 검색어

		GoogleBook book = restTemplate.getForObject(requestUrl, GoogleBook.class);

		return book.getItems();
	}
}
