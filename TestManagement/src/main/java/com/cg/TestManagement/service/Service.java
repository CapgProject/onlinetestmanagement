package com.cg.TestManagement.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.Test;
import com.cg.TestManagement.dto.User;

public interface Service {

	public Test addTest(Test test);
	public Test updateTest(BigInteger testId, Test test);
	public Test deleteTest(BigInteger testId);
	public Question addQuestion(BigInteger testId, Question question);
	public Question updateQuestion(BigInteger testId, BigInteger questionId, Question question);
	public Question deleteQuestion(BigInteger testId, BigInteger questionId);
	public User registerUser(User user);
	public Boolean answerQuestion(Test test, Question question, Integer chosenAnswer);
	public Question showQuestion(Test test, BigInteger questionId);
	public Boolean assignTest(Long userId, BigInteger testId);
	public BigDecimal getResult(Test test);
	public BigDecimal calculateTotalMarks(Test test);
	public Set<Question> showAllQuestions(Test test);
	public Map<BigInteger,Question> showQuestions();
	public Map<BigInteger,Test> showTests();
	public Map<Long, User> showUsers();
	public Test searchTest(BigInteger testId);
	public User searchUser(Long userId);
}
