package com.server.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

@Getter
public enum Category {
	ALL("ALL"), EXERCISE("EXERCISE"), RESTAURANT("RESTAURANT"), MOVIE("MOVIE"), STUDY("STUDY"), ETC("ETC");

	String category;

	Category(String string) {

	}

	public void setCategory(String category) {
		this.category = category;
	}

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static Category createCateogry(String category) {
		for(Category ct : Category.values()) {
			if(ct.name().equals(category)) {
				return ct;
			}
		}
		return null;
	}

}
