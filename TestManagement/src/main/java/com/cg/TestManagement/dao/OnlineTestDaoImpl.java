package com.cg.TestManagement.dao;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;


public class OnlineTestDaoImpl implements OnlineTestDao{
	
	Map<BigInteger, OnlineTest> testDb = new HashMap<BigInteger, OnlineTest >();
	Map<Long , User> userDb = new HashMap<Long, User>();
	Map<BigInteger,Question> questionDb = new HashMap<BigInteger, Question>();


	public OnlineTest saveTest(OnlineTest onlineTest) {
		// TODO Auto-generated method stub
		testDb.put(onlineTest.getTestId(), onlineTest);
		return onlineTest;
	}

	public OnlineTest searchTest(BigInteger testId) {
		// TODO Auto-generated method stub
		return  testDb.get(testId);
	}

	public OnlineTest removeTest(BigInteger testId) {
		// TODO Auto-generated method stub
		return testDb.remove(testId);
	}

	public Map<BigInteger, OnlineTest> showTests() {
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

	public Map<BigInteger, Question> showQuestions() {
		// TODO Auto-generated method stub
		return questionDb;
	}

	public User searchUser(Long userId) {
		// TODO Auto-generated method stub
		return  userDb.get(userId);
	}

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		userDb.put(user.getUserId(), user);
		return user;
	}

	public Map<Long, User> showUsers() {
		// TODO Auto-generated method stub
		return userDb;
	}
}
