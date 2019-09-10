package com.cg.TestManagement.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

import com.cg.TestManagement.Exception.UserException;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.Test;
import com.cg.TestManagement.dto.User;

public interface Service {

	public Test addTest(Test test);
	public Test updateTest(BigInteger testId, Test test) throws UserException;
	public Test deleteTest(BigInteger testId);
	public Question addQuestion(BigInteger testId, Question question) throws UserException;
	public Question updateQuestion(BigInteger testId, BigInteger questionId, Question question) throws UserException;
	public Question deleteQuestion(BigInteger testId, BigInteger questionId) throws UserException;
	public User registerUser(User user);
	public Boolean answerQuestion(Test test, Question question, Integer chosenAnswer) throws UserException;
	public Question showQuestion(Test test, BigInteger questionId) throws UserException;
	public Boolean assignTest(Long userId, BigInteger testId) throws UserException;
	public BigDecimal getResult(Test test);
	public BigDecimal calculateTotalMarks(Test test);
	public Set<Question> showAllQuestions(Test test);
	public Map<BigInteger,Question> showQuestions();
	public Map<BigInteger,Test> showTests();
	public Map<Long, User> showUsers();
	public Test searchTest(BigInteger testId);
	public User searchUser(Long userId);
	public void validateUserId(Long id) throws UserException;
	public void validateTestId(BigInteger id) throws UserException;
	public void validateQuestionId(BigInteger id) throws UserException;
	public void validateUserName(String name) throws UserException;
	public void validatePassword(String password) throws UserException;
	public void validateDate(LocalDateTime startDate, LocalDateTime endDate) throws UserException;
	public void validateTestDuration(LocalTime duration, LocalDateTime startDate, LocalDateTime endDate) throws UserException;
	public void validateEndTime(LocalDateTime endDate) throws UserException;

}
