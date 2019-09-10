package com.cg.TestManagement.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

import com.cg.TestManagement.Exception.ExceptionMessage;
import com.cg.TestManagement.Exception.UserException;
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

	public Boolean answerQuestion(Test test, Question question, Integer chosenAnswer) throws UserException {
		// TODO Auto-generated method stub
		if(!test.getTestQuestions().contains(question)) {
			throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		}
		question.setChosenAnswer(chosenAnswer);
		return true;
	}

	public Question showQuestion(Test test, BigInteger questionId)throws UserException {
		// TODO Auto-generated method stub
		Question question = dao.searchQuestion(questionId);
		if(test.getTestQuestions().contains(question)) {
			throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		}
		return question;
	}

	public Boolean assignTest(Long userId, BigInteger testId) throws UserException {
		// TODO Auto-generated method stub
		User user = dao.searchUser(userId);
		Test test = dao.searchTest(testId);
		if(user == null ) {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}
		if(test == null) {
			throw new UserException(ExceptionMessage.TESTMESSAGE);
		}
		else {
			user.setUserTest(test);
		}
		return true;
	}

	public Test addTest(Test test) {
		return dao.saveTest(test);
	}

	public Test updateTest(BigInteger testId, Test test) throws UserException {
		// TODO Auto-generated method stub
		Test temp = dao.searchTest(testId);
		if (temp!= null){
			dao.removeTest(testId);
			dao.saveTest(test);
			return test;
		}
		else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	public Test deleteTest(BigInteger testId) {
		// TODO Auto-generated method stub
		return dao.removeTest(testId);
	}

	public Question addQuestion(BigInteger testId, Question question) throws UserException{
		// TODO Auto-generated method stub
		Test temp = dao.searchTest(testId);
		if(temp!= null) {
			Set<Question> quests = temp.getTestQuestions();
			quests.add(question);
			dao.saveQuestion(question);
			temp.setTestQuestions(quests);
			return question;
		}
		else
			throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
	}

	public Question updateQuestion(BigInteger testId, BigInteger questionId, Question question) throws UserException {
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
				throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		}
		else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	public Question deleteQuestion(BigInteger testId, BigInteger questionId) throws UserException {
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
				throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		}
		else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
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

	public Map<BigInteger, Question> showQuestions() {
		// TODO Auto-generated method stub
		return dao.showQuestions();
	}

	public Map<BigInteger, Test> showTests() {
		// TODO Auto-generated method stub
		return dao.showTests();
	}

	public Map<Long, User> showUsers() {
		// TODO Auto-generated method stub
		return dao.showUsers();
	}

	public User searchUser(Long userId) {
		// TODO Auto-generated method stub
		return dao.searchUser(userId);
	}

	public Test searchTest(BigInteger testId) {
		// TODO Auto-generated method stub
		return dao.searchTest(testId);
	}
	
	public void validateUserId(Long id) throws UserException {
		if(id <= 0) {
			throw new UserException(ExceptionMessage.IDMESSAGE);
		}
	}
	
	public void validateTestId(BigInteger id) throws UserException {
		if(id.longValue()<=0) {
			throw new UserException(ExceptionMessage.IDMESSAGE);
		}
	}
	
	public void validateQuestionId(BigInteger id) throws UserException {
		if(id.longValue()<=0) {
			throw new UserException(ExceptionMessage.IDMESSAGE);
		}
	}
	
	public void validateUserName(String name) throws UserException{
		String pattern = "^[A-Z][A-Za-z 0-9_-]*$";
		if(!(name.matches(pattern) || (name == null))) {
			throw new UserException(ExceptionMessage.NAMEMESSAGE);
		}
	}
	
	public void validatePassword(String password) throws UserException{
		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		if(!(password.matches(pattern))) {
			throw new UserException(ExceptionMessage.PASSWORDMESSAGE);
		}
	}

	public void validateDate(LocalDateTime startDate, LocalDateTime endDate) throws UserException {
		// TODO Auto-generated method stub
		if(startDate.isAfter(endDate)) {
			throw new UserException(ExceptionMessage.TIMEMESSAGE);
		}
	}

	public void validateTestDuration(LocalTime duration, LocalDateTime startDate, LocalDateTime endDate)
			throws UserException {
		// TODO Auto-generated method stub
		long hours = ChronoUnit.HOURS.between(startDate, endDate);
		if(duration.getHour() > hours) {
			throw new UserException(ExceptionMessage.DURATIONMESSAGE);
		}
	}

	public void validateEndTime(LocalDateTime endDate) throws UserException {
		// TODO Auto-generated method stub
		if(endDate.isBefore(LocalDateTime.now())) {
			throw new UserException(ExceptionMessage.ENDTIMEMESSAGE);
		}
	}
	
}
