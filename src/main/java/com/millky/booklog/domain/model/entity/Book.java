package com.millky.booklog.domain.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Book {

	@Id
	long isbn;

	String title;
	String authors;
	String publisher;
	Date publishedDate;
	String imageUrl;
}
