package com.server.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.server.dto.common.EnumMapperType;
import com.server.dto.common.EnumMapperValueDTO;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum RegionEnum implements EnumMapperType {
	
	  GYEONGGI_DO		("경기도")
	, GANGWON_DO		("강원도")
	, CHUNGCHEONGNAM_DO	("충청남도")
	, CHUNGCHEONGBUK_DO	("충청북도")
	, JEOLLANAM_DO		("전라남도")
	, JEOLLABUK_DO		("전라북도")
	, GYEONGSANGNAM_DO	("경상남도")
	, GYEONGSANGBUK_DO	("경상북도")
	, JEJU_DO			("제주특별자치도")
	, GWANGJU			("광주광역시")       
	, DAEGU             ("대구광역시")     
	, DAEJEON           ("대전광역시")     
	, BUSAN             ("부산광역시")     
	, SEOUL             ("서울특별시")     
	, ULSAN             ("울산광역시")     
	, INCHEON           ("인천광역시")     
	, SEJONG_SI 		("세종특별자치시 ")
	;
	
	private String description;
	
	@Override
	public String getCode() {
		return this.name();
	}
	
	@Override
	public String getDescription(){
        return this.description;
    }
	
	public static List<RegionEnum> findAll() {
		return Arrays.stream(values()).collect(Collectors.toList());
	}
	
	public static List<EnumMapperValueDTO> getRegionCodeXDescriptions() {
		return Arrays.stream(RegionEnum.values()).map(EnumMapperValueDTO::new).collect(Collectors.toList());
	}
}
