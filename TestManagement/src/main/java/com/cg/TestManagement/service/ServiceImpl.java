package com.cg.TestManagement.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.Test;
import com.cg.TestManagement.dto.User;

public class ServiceImpl implements Service{

	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return dao.saveUser(user);
	}

	public Boolean answerQuestion(Test test, Question question, Integer chosenAnswer) {
		// TODO Auto-generated method stub
		if(test.getTestQuestions().contains(question)) {
			question.setChosenAnswer(chosenAnswer);
			return true;
		}
		return false;
	}

	public Question showQuestion(Test test, BigInteger questionId) {
		// TODO Auto-generated method stub
		Question question = dao.searchQuestion(questionId);
		if(test.getTestQuestions().contains(question)) {
			return question;
		}
		return null;
	}

	public Boolean assignTest(Long userId, BigInteger testId) {
		// TODO Auto-generated method stub
		User user = dao.searchUser(userId);
		Test test = dao.searchTest(testId);
		if(test == null) {
			return false;
		}
		else {
			user.setUserTest(test);
		}
		return true;
	}

	

	
}
