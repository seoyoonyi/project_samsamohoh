package com.server.responsecode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
	String name;
	String token = "1616271188";

	public Data(String name) {
		this.name = name;
	}
}
