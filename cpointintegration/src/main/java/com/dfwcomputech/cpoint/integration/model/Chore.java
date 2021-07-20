package com.dfwcomputech.cpoint.integration.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull(message="Points cannot be null ")
	@Min(value = 1, message = "Points have to be bigger than 0")
	private Integer points;
	private LocalDateTime created;
	private LocalDateTime updated;
}
