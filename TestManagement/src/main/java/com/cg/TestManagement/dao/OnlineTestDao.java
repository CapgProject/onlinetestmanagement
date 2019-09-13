package com.cg.TestManagement.dao;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.User;
import java.math.BigInteger;
import java.util.Map;

public interface OnlineTestDao {
	
	public OnlineTest saveTest(OnlineTest onlineTest);
	public OnlineTest searchTest(BigInteger testId);
	public OnlineTest removeTest(BigInteger testId);
	public Map<BigInteger, OnlineTest>  showTests();
	public Question saveQuestion(Question question);
	public Question searchQuestion(BigInteger questId);
	public Question removeQuestion(BigInteger questId);
	public Map<BigInteger,Question> showQuestions();
	public User searchUser(Long userId);
	public User saveUser(User user);	
	public Map<Long,User> showUsers();
	
}