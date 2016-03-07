package com.millky.booklog.domain.model.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class GoogleBook {
	String kind;
	int totalItems;
	List<Item> items;

	@Data
	public static class Item {
		String kind;
		String id;
		Volume volumeInfo;
	}

	@Data
	public static class Volume {
		String title;
		int pageCount;
		List<String> authors;
		String publisher;

		Date publishedDate;

		List<Isbn> industryIdentifiers;
		ImageLinks imageLinks;

		String language;
		String infoLink;
	}

	@Data
	public static class Isbn {
		String type;
		String identifier;
	}

	@Data
	public static class ImageLinks {
		String smallThumbnail;
		String thumbnail;
	}
}
