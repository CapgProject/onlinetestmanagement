package com.cg.TestManagement.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import com.cg.TestManagement.Exception.ExceptionMessage;
import com.cg.TestManagement.Exception.UserException;
import com.cg.TestManagement.dao.OnlineTestDao;
import com.cg.TestManagement.dao.OnlineTestDaoImpl;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;

public class ServiceImpl implements Service {

	OnlineTestDao onlineTestDao = new OnlineTestDaoImpl();

	public User registerUser(User user) throws UserException {
		User returnedUser;
		if ((returnedUser = onlineTestDao.saveUser(user)) != null)
			return returnedUser;
		else {
			throw new UserException(ExceptionMessage.DATABASEMESSAGE);
		}
	}

	public Boolean answerQuestion(OnlineTest onlineTest, Question question, Integer chosenAnswer) throws UserException {
		
		// answerQuestion method is used to check whether the chosen answer is correct or not
		
		if (!onlineTest.getTestQuestions().contains(question)) {
			throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		}
		question.setChosenAnswer(chosenAnswer);
		if (question.getChosenAnswer() == question.getQuestionAnswer()) {
			question.setMarksScored(question.getQuestionMarks());
		} else {
			question.setMarksScored(new Double(0.0));
		}
		onlineTestDao.updateQuestion(question);
		return true;
	}

	public Question showQuestion(OnlineTest onlineTest, BigInteger questionId) throws UserException {
		
		// showQuestion method is used to show questions in a Test
		
		Question question = onlineTestDao.searchQuestion(questionId);
		if (question == null || !onlineTest.getTestQuestions().contains(question)) {
			throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		}
		return question;
	}

	public Boolean assignTest(BigInteger userId, BigInteger testId) throws UserException {
		
		// assignTest method is used to assign a test to a User
		
		User user = onlineTestDao.searchUser(userId);
		OnlineTest onlineTest = onlineTestDao.searchTest(testId);
		if (user == null) {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}
		if (onlineTest == null) {
			throw new UserException(ExceptionMessage.TESTMESSAGE);
		}
		if (onlineTest.getIsTestAssigned()) {
			throw new UserException(ExceptionMessage.TESTASSIGNEDMESSAGE);
		} else {
			user.setUserTest(onlineTest);
			onlineTest.setIsTestAssigned(true);
		}
		onlineTestDao.updateTest(onlineTest);
		onlineTestDao.updateUser(user);
		return true;
	}

	public OnlineTest addTest(OnlineTest onlineTest) throws UserException {
		
		// addTest method is used to add a Test
		
		
		Set<Question> mySet = new HashSet<Question>();
		onlineTest.setTestQuestions(mySet);
		onlineTest.setIsTestAssigned(false);
		OnlineTest returnedTest = onlineTestDao.saveTest(onlineTest);
		if (returnedTest == null) {
			throw new UserException(ExceptionMessage.DATABASEMESSAGE);
		}
		return returnedTest;
	}

	public OnlineTest updateTest(BigInteger testId, OnlineTest onlineTest) throws UserException {
		
		// updateTest method is used to update a Test
		
		OnlineTest temp = onlineTestDao.searchTest(testId);
		if (temp != null) {
			onlineTest.setIsTestAssigned(temp.getIsTestAssigned());
			onlineTest.setTestTotalMarks(temp.getTestTotalMarks());
			onlineTestDao.updateTest(onlineTest);
			return onlineTest;
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	public OnlineTest deleteTest(BigInteger testId) throws UserException {
		
		//deleteTest method is used to delete a Test
		
		OnlineTest returnedTest = onlineTestDao.removeTest(testId);
		if (returnedTest == null) {
			throw new UserException(ExceptionMessage.TESTMESSAGE);
		}
		return returnedTest;
	}

	public Question addQuestion(BigInteger testId, Question question) throws UserException {
		
		// addQuestion method is used to add question to a Test
		
		OnlineTest temp = onlineTestDao.searchTest(testId);
		if (temp != null) {
			Set<Question> quests = temp.getTestQuestions();
			quests.add(question);
			question.setChosenAnswer(-1);
			question.setMarksScored(0.0);
			question.setTestId(testId);
			temp.setTestTotalMarks(temp.getTestTotalMarks() + question.getQuestionMarks());
			onlineTestDao.saveQuestion(question);
			onlineTestDao.updateTest(temp);
			return question;
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	public Question updateQuestion(BigInteger testId, BigInteger questionId, Question question) throws UserException {
		
		//updateQuestion method is used to update a question
		
		OnlineTest temp = onlineTestDao.searchTest(testId);
		if (temp != null) {
			Set<Question> quests = temp.getTestQuestions();
			Question tempQuestion = onlineTestDao.searchQuestion(questionId);
			if (tempQuestion != null && quests.contains(tempQuestion)) {
				quests.remove(tempQuestion);
				quests.add(question);
				question.setChosenAnswer(tempQuestion.getChosenAnswer());
				question.setMarksScored(tempQuestion.getMarksScored());
				question.setTestId(testId);
				question.setQuestionId(questionId);
				onlineTestDao.updateQuestion(question);
				temp.setTestTotalMarks(temp.getTestTotalMarks()-tempQuestion.getQuestionMarks() + question.getQuestionMarks());
				onlineTestDao.updateTest(temp);
				return question;
			} else
				throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	public Question deleteQuestion(BigInteger testId, BigInteger questionId) throws UserException {
		
		// deleteQuestion method is used to delete a question
		
		OnlineTest temp = onlineTestDao.searchTest(testId);
		if (temp != null) {
			Set<Question> quests = temp.getTestQuestions();
			Question tempQuestion = onlineTestDao.searchQuestion(questionId);
			if (tempQuestion != null && quests.contains(tempQuestion)) {
				quests.remove(tempQuestion);
				temp.setTestQuestions(quests);
				temp.setTestTotalMarks(temp.getTestTotalMarks()- tempQuestion.getQuestionMarks());
				onlineTestDao.updateTest(temp);
				return onlineTestDao.removeQuestion(questionId);
			} else
				throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	public Double getResult(OnlineTest onlineTest) throws UserException {
		
		// getResult method is used to get the marks scored by the User
		
		calculateTotalMarks(onlineTest);
		onlineTest.setIsTestAssigned(false);
		onlineTestDao.updateTest(onlineTest);
		return onlineTest.getTestMarksScored();
	}

	public Double calculateTotalMarks(OnlineTest onlineTest) throws UserException {
		
		// calculateTotalMarks method is used to calculate the total marks 

		Double score = new Double(0.0);
		for (Question question : onlineTest.getTestQuestions()) {
			score = score + question.getMarksScored();
		}
		onlineTest.setTestMarksScored(score);
		onlineTestDao.updateTest(onlineTest);
		return score;
	}

	public User searchUser(BigInteger userId) throws UserException {
		
		//searchUser method is used to search a user
		
		User returnedUser = onlineTestDao.searchUser(userId);
		if (returnedUser != null) {
			return returnedUser;
		} else {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}

	}

	public OnlineTest searchTest(BigInteger testId) throws UserException {

		//searchTest method is used to search a test
		
		OnlineTest returnedTest = onlineTestDao.searchTest(testId);
		if (returnedTest != null) {
			return returnedTest;
		} else {
			throw new UserException(ExceptionMessage.TESTMESSAGE);
		}
	}

	public void validateUserId(BigInteger id) throws UserException {
		
		// validateUserId method is used to validate user id as it can't be negative or null
		
		if (id.longValue() <= 0) {
			throw new UserException(ExceptionMessage.IDMESSAGE);
		}
	}

	public void validateTestId(BigInteger id) throws UserException {
		
		// validateTestId method is used to validate test id as it can't be negative or null
		
		if (id.longValue() <= 0) {
			throw new UserException(ExceptionMessage.IDMESSAGE);
		}
	}

	public void validateQuestionId(BigInteger id) throws UserException {

		// validateQuestionId method is used to validate question id as it can't be negative or null
		
		if (id.longValue() <= 0) {
			throw new UserException(ExceptionMessage.IDMESSAGE);
		}
	}

	public void validateUserName(String name) throws UserException {

		// validateUser method is used to validate user name to ensure that it contains only alphabets and the first character is in uuper case
		
		String pattern = "^[A-Z][A-Za-z 0-9_-]*$";
		if (!(name.matches(pattern) || (name == null))) {
			throw new UserException(ExceptionMessage.NAMEMESSAGE);
		}
	}

	public void validatePassword(String password) throws UserException {

		// validatePassword method is used to validate password to ensure that it contains at least one upper case character, one lower case character, one numeric character, one special character and length should be at least eight characters
		
		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		if (!(password.matches(pattern))) {
			throw new UserException(ExceptionMessage.VALIDATEMESSAGE);
		}
	}

	public void validateDate(LocalDateTime startDate, LocalDateTime endDate) throws UserException {
		
		// validateDate method is used to validate date as End date cannot be before start date
		
		if (startDate.isAfter(endDate)) {
			throw new UserException(ExceptionMessage.TIMEMESSAGE);
		}
	}

	public void validateTestDuration(LocalTime duration, LocalDateTime startDate, LocalDateTime endDate)
			throws UserException {

		// validateTestDuration is used to validate date as the test duration cannot be more than the time between the start and end time
		
		long hours = ChronoUnit.HOURS.between(startDate, endDate);
		if (duration.getHour() > hours) {
			throw new UserException(ExceptionMessage.DURATIONMESSAGE);
		}
	}

	public void validateEndTime(LocalDateTime endDate) throws UserException {
		
		// validateEndTime method is used to validate end time as End date cannot be in the past
		
		if (endDate.isBefore(LocalDateTime.now())) {
			throw new UserException(ExceptionMessage.ENDTIMEMESSAGE);
		}
	}

	public void questionAnswerValidate(Integer questionAnswer) throws UserException {
		
		//  validateAnswerValidate method is used to validate answer as the Question Answer can only be in the range of 0 to 3
		
		if (questionAnswer < 0 || questionAnswer > 3) {
			throw new UserException(ExceptionMessage.INVALIDQUESTIONANSWER);
		}
	}
	
	public User updateProfile(User user) throws UserException {
		
		// updateProfile method is used to update the profile of the user
		
		User returnedUser = onlineTestDao.updateUser(user);
		if(returnedUser == null) {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}
		return returnedUser;		
	}

}
