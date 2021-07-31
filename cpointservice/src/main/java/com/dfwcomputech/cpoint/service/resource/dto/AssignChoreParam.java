package com.dfwcomputech.cpoint.service.resource.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AssignChoreParam {
	@Setter@Getter
	private String userName;
	@Setter@Getter
	private String choreName;
	@Setter@Getter
	private LocalDate date;
}
