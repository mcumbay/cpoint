package com.dfwcomputech.cpoint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.common.util.BeanValidator;
import com.dfwcomputech.cpoint.integration.model.Chore;
import com.dfwcomputech.cpoint.integration.repository.ChoreRepository;
import com.dfwcomputech.cpoint.service.IChoreService;

@Service
public class ChoreService implements IChoreService{

	@Autowired
	private ChoreRepository choreRepository;
	
	@Autowired
	private BeanValidator beanValidator;

	@Override
	public Chore createChore(Chore chore) throws CPointException{
		beanValidator.validateBean(chore);
		return choreRepository.save(chore);
	}
	
}
