package com.cg.TestManagement.dao;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.Test;
import com.cg.TestManagement.dto.User;




public class DaoImpl implements Dao{
	
	Map<BigInteger, Test> testDb = new HashMap<BigInteger, Test >();
	Map<Long , User> userDb = new HashMap<Long, User>();
	Map<BigInteger,Question> questionDb = new HashMap<BigInteger, Question>();
	
	
	

	public Test saveTest(Test test) {
		// TODO Auto-generated method stub
		testDb.put(test.getTestId(), test);
		return test;
		
	}

	public Test searchTest(BigInteger testId) {
		// TODO Auto-generated method stub
		return  testDb.get(testId);
	}

	public Test removeTest(BigInteger testId) {
		// TODO Auto-generated method stub
		return testDb.remove(testId);
	}

	public Map<?, ?> showAllTest() {
		// TODO Auto-generated method stub
		return testDb;
	}

	public Question saveQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDb.put(question.getQuestionId(), question);
		return question;
	}

	public Question searchQuestion(BigInteger questId) {
		// TODO Auto-generated method stub
		return  questionDb.get(questId);
	}

	public Question removeQuestion(BigInteger questId) {
		// TODO Auto-generated method stub
		return questionDb.remove(questId);
	}

	public Map<?, ?> showAllQuestions() {
		// TODO Auto-generated method stub
		return questionDb;
	}

	public User searchUser(Long UserId) {
		// TODO Auto-generated method stub
		return  userDb.get(UserId);
	}

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		userDb.put(user.getUserId(), user);
		return user;
	}
	
	
	
	
}
