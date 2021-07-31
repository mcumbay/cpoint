package com.dfwcomputech.cpoint.integration.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dfwcomputech.cpoint.integration.model.UserChore;

public interface UserChoreRepository extends JpaRepository<UserChore, Long>{

	UserChore findFirstByUserUserNameAndChoreNameAndDate(String userName, String choreName, LocalDate date);

}
