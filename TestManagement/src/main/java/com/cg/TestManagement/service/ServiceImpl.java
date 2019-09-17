package com.cg.TestManagement.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import com.cg.TestManagement.dao.OnlineTestDao;
import com.cg.TestManagement.dao.OnlineTestDaoImpl;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;
import com.cg.TestManagement.exception.ExceptionMessage;
import com.cg.TestManagement.exception.UserException;

public class ServiceImpl implements Service {

	public static OnlineTestDao onlineTestDao;
	static {
		onlineTestDao = new OnlineTestDaoImpl();
	}
	@Override
	public User registerUser(User user) throws UserException {
		User returnedUser;
		if ((returnedUser = onlineTestDao.saveUser(user)) != null)
			return returnedUser;
		else {
			throw new UserException(ExceptionMessage.DATABASEMESSAGE);
		}
	}

	@Override
	public Boolean answerQuestion(OnlineTest onlineTest, Question question, Integer chosenAnswer) throws UserException {
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

	@Override
	public Question showQuestion(OnlineTest onlineTest, BigInteger questionId) throws UserException {
		Question question = onlineTestDao.searchQuestion(questionId);
		if (question == null || !onlineTest.getTestQuestions().contains(question)) {
			throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		}
		return question;
	}

	@Override
	public Boolean assignTest(BigInteger userId, BigInteger testId) throws UserException {
		User user = onlineTestDao.searchUser(userId);
		OnlineTest onlineTest = onlineTestDao.searchTest(testId);
		if (user == null) {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}
		if(user.getIsAdmin()) {
			throw new UserException(ExceptionMessage.ADMINMESSAGE);
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

	@Override
	public OnlineTest addTest(OnlineTest onlineTest) throws UserException {
		Set<Question> mySet = new HashSet<Question>();
		onlineTest.setTestQuestions(mySet);
		onlineTest.setIsTestAssigned(false);
		OnlineTest returnedTest = onlineTestDao.saveTest(onlineTest);
		if (returnedTest == null) {
			throw new UserException(ExceptionMessage.DATABASEMESSAGE);
		}
		return returnedTest;
	}

	@Override
	public OnlineTest updateTest(BigInteger testId, OnlineTest onlineTest) throws UserException {
		OnlineTest temp = onlineTestDao.searchTest(testId);
		if (temp != null) {
			onlineTest.setIsTestAssigned(temp.getIsTestAssigned());
			onlineTest.setTestTotalMarks(temp.getTestTotalMarks());
			onlineTestDao.updateTest(onlineTest);
			return onlineTest;
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	@Override
	public OnlineTest deleteTest(BigInteger testId) throws UserException {
		OnlineTest returnedTest = onlineTestDao.removeTest(testId);
		if (returnedTest == null) {
			throw new UserException(ExceptionMessage.TESTMESSAGE);
		}
		return returnedTest;
	}

	@Override
	public Question addQuestion(BigInteger testId, Question question) throws UserException {
		OnlineTest temp = onlineTestDao.searchTest(testId);
		if (temp != null) {
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

	@Override
	public Question updateQuestion(BigInteger testId, BigInteger questionId, Question question) throws UserException {
		OnlineTest temp = onlineTestDao.searchTest(testId);
		if (temp != null) {
			Set<Question> quests = temp.getTestQuestions();
			Question tempQuestion = onlineTestDao.searchQuestion(questionId);
			if (tempQuestion != null && quests.contains(tempQuestion)) {
				question.setChosenAnswer(tempQuestion.getChosenAnswer());
				question.setMarksScored(tempQuestion.getMarksScored());
				question.setTestId(testId);
				question.setQuestionId(questionId);
				onlineTestDao.updateQuestion(question);
				temp.setTestTotalMarks(
						temp.getTestTotalMarks() - tempQuestion.getQuestionMarks() + question.getQuestionMarks());
				onlineTestDao.updateTest(temp);
				return question;
			} else
				throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	@Override
	public Question deleteQuestion(BigInteger testId, BigInteger questionId) throws UserException {
		OnlineTest temp = onlineTestDao.searchTest(testId);
		if (temp != null) {
			Set<Question> quests = temp.getTestQuestions();
			Question tempQuestion = onlineTestDao.searchQuestion(questionId);
			if (tempQuestion != null && quests.contains(tempQuestion)) {
				temp.setTestTotalMarks(temp.getTestTotalMarks() - tempQuestion.getQuestionMarks());
				onlineTestDao.updateTest(temp);
				return onlineTestDao.removeQuestion(questionId);
			} else
				throw new UserException(ExceptionMessage.QUESTIONMESSAGE);
		} else
			throw new UserException(ExceptionMessage.TESTMESSAGE);
	}

	@Override
	public Double getResult(OnlineTest onlineTest) throws UserException {
		calculateTotalMarks(onlineTest);
		onlineTest.setIsTestAssigned(false);
		onlineTestDao.updateTest(onlineTest);
		return onlineTest.getTestMarksScored();
	}

	@Override
	public Double calculateTotalMarks(OnlineTest onlineTest) throws UserException {
		Double score = new Double(0.0);
		for (Question question : onlineTest.getTestQuestions()) {
			score = score + question.getMarksScored();
		}
		onlineTest.setTestMarksScored(score);
		onlineTestDao.updateTest(onlineTest);
		return score;
	}

	@Override
	public User searchUser(BigInteger userId) throws UserException {
		User returnedUser = onlineTestDao.searchUser(userId);
		if (returnedUser != null) {
			return returnedUser;
		} else {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}

	}

	@Override
	public OnlineTest searchTest(BigInteger testId) throws UserException {
		OnlineTest returnedTest = onlineTestDao.searchTest(testId);
		if (returnedTest != null) {
			return returnedTest;
		} else {
			throw new UserException(ExceptionMessage.TESTMESSAGE);
		}
	}

	@Override
	public void validateUserId(BigInteger id) throws UserException {
		if (id.longValue() <= 0) {
			throw new UserException(ExceptionMessage.IDMESSAGE);
		}
	}

	@Override
	public void validateTestId(BigInteger id) throws UserException {
		if (id.longValue() <= 0) {
			throw new UserException(ExceptionMessage.IDMESSAGE);
		}
	}

	@Override
	public void validateQuestionId(BigInteger id) throws UserException {
		if (id.longValue() <= 0) {
			throw new UserException(ExceptionMessage.IDMESSAGE);
		}
	}

	@Override
	public void validateUserName(String name) throws UserException {
		String pattern = "^[A-Z][A-Za-z 0-9_-]*$";
		if (!(name.matches(pattern) || (name == null))) {
			throw new UserException(ExceptionMessage.NAMEMESSAGE);
		}
	}

	@Override
	public void validatePassword(String password) throws UserException {
		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		if (!(password.matches(pattern))) {
			throw new UserException(ExceptionMessage.VALIDATEMESSAGE);
		}
	}

	@Override
	public void validateDate(LocalDateTime startDate, LocalDateTime endDate) throws UserException {
		if (startDate.isAfter(endDate)) {
			throw new UserException(ExceptionMessage.TIMEMESSAGE);
		}
	}

	@Override
	public void validateTestDuration(LocalTime duration, LocalDateTime startDate, LocalDateTime endDate)
			throws UserException {
		long hours = ChronoUnit.HOURS.between(startDate, endDate);
		if (duration.getHour() > hours) {
			throw new UserException(ExceptionMessage.DURATIONMESSAGE);
		}
	}

	@Override
	public void validateEndTime(LocalDateTime endDate) throws UserException {
		if (endDate.isBefore(LocalDateTime.now())) {
			throw new UserException(ExceptionMessage.ENDTIMEMESSAGE);
		}
	}

	@Override
	public void questionAnswerValidate(Integer questionAnswer) throws UserException {
		if (questionAnswer < 0 || questionAnswer > 3) {
			throw new UserException(ExceptionMessage.INVALIDQUESTIONANSWER);
		}
	}

	@Override
	public User updateProfile(User user) throws UserException {

		User returnedUser = onlineTestDao.updateUser(user);
		if (returnedUser == null) {
			throw new UserException(ExceptionMessage.USERMESSAGE);
		}
		return returnedUser;
	}

}
