package com.millky.booklog.infrastructure.client;

import com.millky.booklog.domain.model.dto.DaumBook;
import com.millky.booklog.domain.model.exception.IllegalApiKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class DaumBookApiClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${app.key.daum}")
    private String DAUM_SEARCH_APIKEY;

    public List<DaumBook.Document> getDaumBooks(String q, boolean isbn) {

        String requestUrl = "https://dapi.kakao.com/v3/search/book";
        requestUrl += "?size=" + "30"; // 출력될 결과수
        // requestUrl += "&page=" + "1"; // 페이지 번호

        if (isbn) {
            requestUrl += "&target=isbn";
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "KakaoAK " + DAUM_SEARCH_APIKEY);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("query", q);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        log.info("headers = {}", headers);


        log.info("requestUrl = {}", requestUrl);
        log.info("entity = {}", request);

        DaumBook book;
        try {
            book = restTemplate.postForObject(requestUrl, request, DaumBook.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new IllegalApiKeyException("Daum search API key Not Found.");
        }

        log.info("book = {}", book.getDocuments());

        return book.getDocuments();
    }
}
