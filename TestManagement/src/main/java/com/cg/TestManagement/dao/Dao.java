package com.cg.TestManagement.dao;
import com.cg.TestManagement.dto.Test;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.User;
import java.math.BigInteger;
import java.util.Map;

public interface Dao {
	
	public Test saveTest(Test test);
	public Test searchTest(BigInteger testId);
	public Test removeTest(BigInteger testId);
	public Map<BigInteger, Test>  showTests();
	public Question saveQuestion(Question question);
	public Question searchQuestion(BigInteger questId);
	public Question removeQuestion(BigInteger questId);
	public Map<BigInteger,Question> showQuestions();
	public User searchUser(Long userId);
	public User saveUser(User user);	
	public Map<Long,User> showUsers();
	
}