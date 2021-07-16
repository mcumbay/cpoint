package com.dfwcomputech.cpoint.service.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChoreDto {

	private String name;
	private String description;
	private Integer points;

	public ChoreDto(String name, String description, Integer points) {
		this.name = name;
		this.description = description;
		this.points = points;
	}

}
