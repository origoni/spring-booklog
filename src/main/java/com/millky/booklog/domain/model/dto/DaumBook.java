package com.millky.booklog.domain.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DaumBook {

    public Meta meta;
    List<Document> documents;

    @Data
    public static class Meta {
        int totalCount;
        int pageableCount;
        boolean isEnd;
    }

    @Data
    public static class Document {
        String title;
        String contents;
        String url;
        String isbn;
        Date datetime;// 출판일
        List<String> authors;
        String publisher;
        List<String> translators;
        int price;
        int salePrice;
        String category;
        String thumbnail;
    }
}
