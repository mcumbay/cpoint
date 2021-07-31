package com.dfwcomputech.cpoint.service.impl;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.integration.model.Chore;
import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.integration.model.UserChore;
import com.dfwcomputech.cpoint.integration.repository.ChoreRepository;
import com.dfwcomputech.cpoint.integration.repository.UserChoreRepository;
import com.dfwcomputech.cpoint.integration.repository.UserRepository;
import com.dfwcomputech.cpoint.service.IUserService;

@Service
public class UserService implements IUserService{
		
	private UserRepository userRepository;
	
	private ChoreRepository choreRepository;
	
	private UserChoreRepository userChoreRepository;
	
	@Autowired
	public void setChoreRepository(ChoreRepository choreRepository) {
		this.choreRepository=choreRepository;
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Autowired
	public void setUserChoreRepository(UserChoreRepository userChoreRepository) {
		this.userChoreRepository=userChoreRepository;
	}
	
	@Override
	public User createUser(User user) throws CPointException{
		//Guard Validation
		validateUser(user);
		//Verify userName doesnt exist
		if(findUser(user.getUserName())!=null)
			throw new CPointException("User Name already exists.");
		
		return userRepository.save(user);
	}
	
	private void validateUser(User user) throws CPointException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if(!violations.isEmpty())
			throw new CPointException(violations.iterator().next().getMessage());
	}

	@Override
	public User findUser(String userName) {
		if(userName==null ||userName.isBlank())
			return null;
		return userRepository.findFirstByUserName(userName);
	}

	@Override
	public User updateUser(User user) throws CPointException{
		if(user==null ||user.getId()==null)
			throw new CPointException("User Id cannot be null");
		
		validateUser(user);
		return userRepository.save(user);

	}

	@Override
	public void deleteUser(String userName) {
		if(userName==null ||userName.isBlank())
			return;
		User userToDelete = findUser(userName);
		if(userToDelete!=null)
			userRepository.delete(userToDelete);
	}

	@Override
	public void assignChore(String userName, String choreName, LocalDate date) throws CPointException{
		User user = userRepository.findFirstByUserName(userName);
		Chore chore = choreRepository.findFirstByName(choreName);
		
		UserChore userChore = new UserChore(user,chore,date);		
		userChoreRepository.save(userChore);		
	}

}
