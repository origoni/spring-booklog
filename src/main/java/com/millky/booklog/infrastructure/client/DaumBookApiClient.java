package com.millky.booklog.infrastructure.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.millky.booklog.domain.model.dto.DaumBook;
import com.millky.booklog.domain.model.dto.DaumBook.Item;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DaumBookApiClient {

	@Value("${app.key.daum}")
	private String DAUM_SEARCH_APIKEY;

	public List<Item> getDaumBooks(String q, boolean isbn) {

		RestTemplate restTemplate = new RestTemplate();

		String requestUrl = "https://apis.daum.net/search/book";
		requestUrl += "?apikey=" + DAUM_SEARCH_APIKEY; // 발급된 키
		requestUrl += "&q=" + q; // 검색어
		requestUrl += "&result=" + "20"; // 출력될 결과수
		// requestUrl += "&pageno=" + "1"; // 페이지 번호
		requestUrl += "&output=json";

		if (isbn) {
			requestUrl += "&searchType=isbn";
		}

		log.info("requestUrl = {}", requestUrl);

		DaumBook book = restTemplate.getForObject(requestUrl, DaumBook.class);

		return book.getChannel().getItem();
	}
}
