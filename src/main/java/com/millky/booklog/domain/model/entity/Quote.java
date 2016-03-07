package com.millky.booklog.domain.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Quote {

	@Id
	@GeneratedValue
	int id;

	long isbn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "isbn", insertable = false, updatable = false)
	Book book;

	@Min(0)
	@Digits(integer = 10, fraction = 0)
	@NotNull
	int pageNumber;

	@NotNull
	@Size(min = 1, max = 255)
	String words;

	Date recordAt;
}
