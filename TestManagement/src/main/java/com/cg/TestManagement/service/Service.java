package com.cg.TestManagement.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.Test;
import com.cg.TestManagement.dto.User;

public interface Service {

	public User registerUser(User user);
	public Boolean answerQuestion(Test test, Question question, Integer chosenAnswer);
	public Question showQuestion(Test test, BigInteger questionId);
	public Boolean assignTest(Long userId, BigInteger testId);
}
