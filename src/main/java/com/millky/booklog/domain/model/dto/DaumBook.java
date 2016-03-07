package com.millky.booklog.domain.model.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DaumBook {

	public Channel channel;

	@Data
	public static class Channel {
		int result; // 한 페이지에 보여질 결과 수
		String title; // 검색 제목
		int totalCount; // 전체 검색 결과의 수
		String description; // 검색 결과의 간략한 소개
		List<Item> item;
		Date lastBuildDate;
		String generator;
	}

	@Data
	public static class Item {
		String title;// 검색 제목
		String link;// 책에 대한 페이지 link url
		String cover_s_url;// 표지 이미지(small)
		String cover_l_url;// 표지 이미지(large)
		String description;// 책에 대한 설명
		String author;// 저자
		String author_t;// 저자
		String etc_author;// 저자
		String translator;// 역자
		String pub_nm;// 출판사

		@JsonFormat(pattern = "yyyyMMdd")
		Date pub_date;// 출판일

		String category;// 카테고리 정보
		String isbn;// ISBN 번호 (10자리)
		String isbn13;// ISBN 번호 (13자리)
		String sale_yn;// 판매 가능 여부
		String list_price;// 원가격
		String sale_price;// 판매가격
		String status_des;// 책의 현재 상태(정상,품절,절판 등)
		String barcode;// 교보문고 바코드 정보
		String ebook_barcode;// 교보문고 전자 책 바코드 정보
	}
}
