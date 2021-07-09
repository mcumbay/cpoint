package com.dfwcomputech.cpoint.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dfwcomputech.cpoint.integration.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findFirstByUserName(String userName);
}
