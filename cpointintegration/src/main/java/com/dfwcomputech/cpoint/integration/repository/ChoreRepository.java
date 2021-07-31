package com.dfwcomputech.cpoint.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dfwcomputech.cpoint.integration.model.Chore;

public interface ChoreRepository extends JpaRepository<Chore, Long>{
	public List<Chore> findByName(String name);
	public Chore findFirstByName(String name);
}
