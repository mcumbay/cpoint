package com.dfwcomputech.cpoint.integration.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class UserChore {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	@ManyToOne
	@JoinColumn(name="choreId")
	private Chore chore;

	private LocalDate date;

	@NotNull(message="Status cannot be null")
	private ChoreStatus status;

	private LocalDateTime created;

	private LocalDateTime updated;

	public UserChore(User user,Chore chore, LocalDate date) {
		this.user=user;
		this.chore = chore;
		this.date = date;
		this.status= ChoreStatus.CREATED;
		this.created = LocalDateTime.now();		
	}
}
