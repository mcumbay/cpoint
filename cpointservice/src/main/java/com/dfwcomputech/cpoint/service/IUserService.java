package com.dfwcomputech.cpoint.service;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.integration.model.User;

public interface IUserService {
	User createUser(User user) throws CPointException;
	User findUser(String userName);
	User updateUser(User user) throws CPointException;
	void deleteUser(String userName);
	void assignChore(String userName, String choreName) throws CPointException;
}
