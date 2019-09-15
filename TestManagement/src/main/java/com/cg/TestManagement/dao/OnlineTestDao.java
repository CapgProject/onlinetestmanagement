package com.cg.TestManagement.dao;
import com.cg.TestManagement.Exception.UserException;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.User;
import java.math.BigInteger;

public interface OnlineTestDao {
	
	public OnlineTest saveTest(OnlineTest onlineTest) throws UserException;
	public OnlineTest searchTest(BigInteger testId) throws UserException;
	public OnlineTest removeTest(BigInteger testId) throws UserException;
	public Question saveQuestion(Question question) throws UserException;
	public Question searchQuestion(BigInteger questId) throws UserException;
	public Question removeQuestion(BigInteger questId) throws UserException;
	public Question updateQuestion(Question question) throws UserException;
	public User searchUser(BigInteger userId) throws UserException;
	public User saveUser(User user) throws UserException;	
	public User removeUser(BigInteger userId) throws UserException;
	public User updateUser(User user) throws UserException;
	OnlineTest updateTest(OnlineTest test) throws UserException;
	
	
}