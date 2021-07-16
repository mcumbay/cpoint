package com.dfwcomputech.cpoint.integration.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Chore {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message="Chore name cannot be empty")
	private String name;
	private String description;
	private Integer points;
	private LocalDateTime created;
	private LocalDateTime updated;
}
