package com.dfwcomputech.cpoint.service;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.integration.model.Chore;

public interface IChoreService {
	public Chore createChore(Chore chore) throws CPointException;
}
