package com.server.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumMapperValueDTO {
	
	private String code;
	private String description;
	
	public EnumMapperValueDTO(EnumMapperType enumMapperType) {
		code = enumMapperType.getCode();
		description = enumMapperType.getDescription();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("	  code='"+code+'\'');
		sb.append("	 ,description='"+description+'\'');
		sb.append("}");
		return sb.toString();
	}
}
