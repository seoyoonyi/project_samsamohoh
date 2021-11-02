package com.server.responsecode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseCode {
	String code;
	String message;
	Data data;
	
}
