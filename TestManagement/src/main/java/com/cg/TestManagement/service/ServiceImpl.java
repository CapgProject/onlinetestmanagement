package com.cg.TestManagement.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

import com.cg.TestManagement.dao.Dao;
import com.cg.TestManagement.dao.DaoImpl;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.Test;
import com.cg.TestManagement.dto.User;

public class ServiceImpl implements Service{

	Dao dao = new DaoImpl();
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
		if(user == null || test == null) {
			return false;
		}
		else {
			user.setUserTest(test);
		}
		return true;
	}

	public Test addTest(Test test) {
		return dao.saveTest(test);
	}

	public Test updateTest(BigInteger testId, Test test) {
		// TODO Auto-generated method stub
		Test temp = dao.searchTest(testId);
		if (temp!= null){
			dao.removeTest(testId);
			dao.saveTest(test);
			return test;
		}
		else
			return null;
	}

	public Test deleteTest(BigInteger testId) {
		// TODO Auto-generated method stub
		return dao.removeTest(testId);
	}

	public Question addQuestion(BigInteger testId, Question question) {
		// TODO Auto-generated method stub
		Test temp = dao.searchTest(testId);
		if(temp!= null) {
			Set<Question> quests = temp.getTestQuestions();
			quests.add(question);
			temp.setTestQuestions(quests);
			return question;
		}
		else
			return null;
	}

	public Question updateQuestion(BigInteger testId, BigInteger questionId, Question question) {
		// TODO Auto-generated method stub
		Test temp = dao.searchTest(testId);
		if(temp!= null) {
			Set<Question> quests = temp.getTestQuestions();
			Question tempQuestion = dao.searchQuestion(questionId);
			if (quests.contains(tempQuestion)) {
				quests.remove(tempQuestion);
				quests.add(question);
				dao.removeQuestion(questionId);
				dao.saveQuestion(question);
				temp.setTestQuestions(quests);
				return question;
			}
			else
				return null;
		}
		else
			return null;
	}

	public Question deleteQuestion(BigInteger testId, BigInteger questionId) {
		// TODO Auto-generated method stub
		Test temp = dao.searchTest(testId);
		if(temp!= null) {
			Set<Question> quests = temp.getTestQuestions();
			Question tempQuestion = dao.searchQuestion(questionId);
			if(quests.contains(tempQuestion)) {
				quests.remove(tempQuestion);
				dao.removeQuestion(questionId);
				temp.setTestQuestions(quests);
				return tempQuestion;
			}
			else
				return null;
		}
		else
			return null;
	}

	public BigDecimal getResult(Test test) {
		// TODO Auto-generated method stub
		return test.getTestMarksScored();
	}

	public BigDecimal calculateTotalMarks(Test test) {
		// TODO Auto-generated method stub
		BigDecimal score = new BigDecimal(0.0);
		for(Question question: test.getTestQuestions()) {
			score = score.add(question.getMarksScored());
		}
		test.setTestTotalMarks(score);
		return score;
	}

	public Set<Question> showAllQuestions(Test test) {
		// TODO Auto-generated method stub
		return test.getTestQuestions();
	}

	public Map<?, ?> showQuestions() {
		// TODO Auto-generated method stub
		return dao.showQuestions();
	}

	public Map<?, ?> showTests() {
		// TODO Auto-generated method stub
		return dao.showTests();
	}

	public Map<?, ?> showUsers() {
		// TODO Auto-generated method stub
		return dao.showQuestions();
	}

	public User searchUser(Long userId) {
		// TODO Auto-generated method stub
		return dao.searchUser(userId);
	}	
}
