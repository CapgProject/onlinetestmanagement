package com.cg.TestManagement.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.cg.TestManagement.Exception.UserException;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;


public interface Service {
	public OnlineTest addTest(OnlineTest onlineTest) throws UserException;
	public OnlineTest updateTest(BigInteger testId, OnlineTest onlineTest) throws UserException;
	public OnlineTest deleteTest(BigInteger testId) throws UserException;
	public Question addQuestion(BigInteger testId, Question question) throws UserException;
	public Question updateQuestion(BigInteger testId, BigInteger questionId, Question question) throws UserException;
	public Question deleteQuestion(BigInteger testId, BigInteger questionId) throws UserException;
	public User registerUser(User user) throws UserException;
	public Boolean answerQuestion(OnlineTest onlineTest, Question question, Integer chosenAnswer) throws UserException;
	public Question showQuestion(OnlineTest onlineTest, BigInteger questionId) throws UserException;
	public Boolean assignTest(BigInteger userId, BigInteger testId) throws UserException;
	public Double getResult(OnlineTest onlineTest) throws UserException;
	public Double calculateTotalMarks(OnlineTest onlineTest) throws UserException;
	public OnlineTest searchTest(BigInteger testId) throws UserException;
	public User searchUser(BigInteger userId) throws UserException;
	public void validateUserId(BigInteger id) throws UserException;
	public void validateTestId(BigInteger id) throws UserException;
	public void validateQuestionId(BigInteger id) throws UserException;
	public void validateUserName(String name) throws UserException;
	public void validatePassword(String password) throws UserException;
	public void validateDate(LocalDateTime startDate, LocalDateTime endDate) throws UserException;
	public void validateTestDuration(LocalTime duration, LocalDateTime startDate, LocalDateTime endDate) throws UserException;
	public void validateEndTime(LocalDateTime endDate) throws UserException;
	public void questionAnswerValidate(Integer questionAnswer) throws UserException;
	public User updateProfile(User user) throws UserException;
	
}
