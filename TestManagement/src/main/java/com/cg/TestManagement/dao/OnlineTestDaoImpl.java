package com.cg.TestManagement.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;
import com.cg.TestManagement.exception.UserException;
import com.cg.TestManagement.util.DbUtil;

public class OnlineTestDaoImpl implements OnlineTestDao {

	private static final String TESTID = "test_id";
	private static Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private static Logger myLogger;

	static {

		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("OnlineTestDaoImpl.class");
	}

	static {
		try {
			connection = DbUtil.getConnection();
			myLogger.info("Connection obtained");
		} catch (UserException e) {
			myLogger.error("Connection not obtained at Employee Dao" + e);
		}
	}

	public void closePreparedStatement() {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				myLogger.error(e);
			}
		}
	}
	
	public Set<Question> getQuestionSet(BigInteger testId) throws UserException {
		PreparedStatement questionStatement = null;
		Set<Question> testQuestions = new HashSet<Question>();
		try {
			questionStatement = connection.prepareStatement(SqlUtil.QUESTIONSETSQL);
			questionStatement.setLong(1, testId.longValue());
			ResultSet questionResult = questionStatement.executeQuery();
			while (questionResult.next()) {
				Question question = new Question();
				question.setQuestionId(BigInteger.valueOf(questionResult.getLong("question_id")));
				question.setQuestionTitle(questionResult.getString("question_title"));
				question.setQuestionAnswer(questionResult.getInt("question_correct_answer"));
				question.setChosenAnswer(questionResult.getInt("question_chosen_answer"));
				question.setQuestionMarks(questionResult.getDouble("question_marks"));
				question.setMarksScored(questionResult.getDouble("question_marks_scored"));
				String[] questionOptions = { questionResult.getString("question_option_a"),
						questionResult.getString("question_option_b"), questionResult.getString("question_option_c"),
						questionResult.getString("question_option_d") };
				question.setQuestionOptions(questionOptions);
				question.setTestId(testId);
				testQuestions.add(question);
			}
		} catch (SQLException e) {
			myLogger.error("Error at search question in searchTest DAO " + e);
		} finally {
			if (questionStatement != null) {
				try {
					questionStatement.close();
				} catch (Exception e2) {
					myLogger.error(e2);
				}
			}
		}
		return testQuestions;
	}

	@Override
	public OnlineTest saveTest(OnlineTest onlineTest) throws UserException {
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.SAVETESTSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, onlineTest.getTestName());
			preparedStatement.setTime(2, Time.valueOf(onlineTest.getTestDuration()));
			preparedStatement.setDouble(3, onlineTest.getTestTotalMarks());
			preparedStatement.setDouble(4, onlineTest.getTestMarksScored());
			preparedStatement.setTimestamp(5, Timestamp.valueOf(onlineTest.getStartTime()));
			preparedStatement.setTimestamp(6, Timestamp.valueOf(onlineTest.getEndTime()));
			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				return null;
			}
			BigInteger generatedId = BigInteger.valueOf(0L);
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = BigInteger.valueOf(resultSet.getLong(1));
			}
			onlineTest.setTestId(generatedId);
		} catch (SQLException e) {
			myLogger.error("Error at add Test Dao method: " + e);
		} finally {
			closePreparedStatement();
		}
		return onlineTest;
	}

	@Override
	public OnlineTest searchTest(BigInteger testId) throws UserException {
		OnlineTest onlineTest = new OnlineTest();
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.SEARCHTESTSQL);
			preparedStatement.setLong(1, testId.longValue());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				onlineTest.setTestId(BigInteger.valueOf(resultSet.getLong(TESTID)));
				onlineTest.setTestName(resultSet.getString("test_name"));
				onlineTest.setTestDuration(resultSet.getTime("test_duration").toLocalTime());
				onlineTest.setTestTotalMarks(resultSet.getDouble("test_total_marks"));
				onlineTest.setTestMarksScored(resultSet.getDouble("test_marks_scored"));
				onlineTest.setStartTime(resultSet.getTimestamp("test_start_date_time").toLocalDateTime());
				onlineTest.setEndTime(resultSet.getTimestamp("test_end_date_time").toLocalDateTime());
				onlineTest.setIsTestAssigned(resultSet.getBoolean("test_is_assigned"));
				onlineTest.setTestQuestions(getQuestionSet(testId));
			} else {
				return null;
			}

		} catch (SQLException e) {
			myLogger.error("Error at search Test Dao method: " + e);
		} finally {
			closePreparedStatement();
		}
		return onlineTest;
	}

	@Override
	public OnlineTest removeTest(BigInteger testId) throws UserException {
		OnlineTest onlineTest = searchTest(testId);
		if (onlineTest == null) {
			return onlineTest;
		}
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.REMOVETESTSQL);
			preparedStatement.setLong(1, testId.longValue());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			myLogger.error("Error at remove Test Dao method: " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					myLogger.error("Error at remove test Dao method" + e);
				}
			}
		}
		return onlineTest;
	}

	@Override
	public OnlineTest updateTest(OnlineTest test) throws UserException {
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.UPDATETESTSQL);
			preparedStatement.setString(1, test.getTestName());
			preparedStatement.setTime(2, Time.valueOf(test.getTestDuration()));
			preparedStatement.setDouble(3, test.getTestTotalMarks());
			preparedStatement.setDouble(4, test.getTestMarksScored());
			preparedStatement.setTimestamp(5, Timestamp.valueOf(test.getStartTime()));
			preparedStatement.setTimestamp(6, Timestamp.valueOf(test.getEndTime()));
			preparedStatement.setBoolean(7, test.getIsTestAssigned());
			preparedStatement.setLong(8, test.getTestId().longValue());
			int noOfRec = preparedStatement.executeUpdate();
			if (noOfRec == 0) {
				return null;
			}
		} catch (SQLException e) {
			myLogger.error("Error at update test Dao method : " + e);
		} finally {
			closePreparedStatement();
		}
		return test;
	}

	@Override
	public Question saveQuestion(Question question) throws UserException {
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.SAVEQUESTIONSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, question.getQuestionTitle());
			preparedStatement.setString(2, question.getQuestionOptions()[0]);
			preparedStatement.setString(3, question.getQuestionOptions()[1]);
			preparedStatement.setString(4, question.getQuestionOptions()[2]);
			preparedStatement.setString(5, question.getQuestionOptions()[3]);
			preparedStatement.setInt(6, question.getChosenAnswer());
			preparedStatement.setInt(7, question.getQuestionAnswer());
			preparedStatement.setDouble(8, question.getQuestionMarks());
			preparedStatement.setDouble(9, question.getMarksScored());
			preparedStatement.setLong(10, question.getTestId().longValue());
			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				return null;
			}
			BigInteger generatedId = BigInteger.valueOf(0L);
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = BigInteger.valueOf(resultSet.getLong(1));
			}
			question.setQuestionId(generatedId);
		} catch (SQLException e) {
			myLogger.error("Error at add Question Dao method: " + e);
		} finally {
			closePreparedStatement();
		}
		return question;
	}

	@Override
	public Question searchQuestion(BigInteger questId) throws UserException {
		Question question = new Question();
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.SEARCHQUESTIONSQL);
			preparedStatement.setLong(1, questId.longValue());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				question.setQuestionId(BigInteger.valueOf(resultSet.getLong("question_id")));
				question.setQuestionTitle(resultSet.getString("question_title"));
				question.setQuestionAnswer(resultSet.getInt("question_correct_answer"));
				question.setChosenAnswer(resultSet.getInt("question_chosen_answer"));
				question.setQuestionMarks(resultSet.getDouble("question_marks"));
				question.setMarksScored(resultSet.getDouble("question_marks_scored"));
				String[] questionOptions = { resultSet.getString("question_option_a"),
						resultSet.getString("question_option_b"), resultSet.getString("question_option_c"),
						resultSet.getString("question_option_d") };
				question.setQuestionOptions(questionOptions);
				question.setTestId(BigInteger.valueOf(resultSet.getLong(TESTID)));
			} else {
				return null;
			}
		} catch (SQLException e) {
			myLogger.error("Error at search Question Dao method: " + e);
		} finally {
			closePreparedStatement();
		}
		return question;
	}

	@Override
	public Question removeQuestion(BigInteger questId) throws UserException {
		Question question = searchQuestion(questId);
		if (question == null) {
			return question;
		}
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.REMOVEQUESTIONSQL);
			preparedStatement.setLong(1, questId.longValue());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			myLogger.error("Error at add remove question method: " + e);
		} finally {
			closePreparedStatement();
		}
		return question;
	}

	@Override
	public Question updateQuestion(Question question) throws UserException {
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.UPDATEQUESTIONSQL);
			preparedStatement.setString(1, question.getQuestionTitle());
			preparedStatement.setString(2, question.getQuestionOptions()[0]);
			preparedStatement.setString(3, question.getQuestionOptions()[1]);
			preparedStatement.setString(4, question.getQuestionOptions()[2]);
			preparedStatement.setString(5, question.getQuestionOptions()[3]);
			preparedStatement.setInt(6, question.getChosenAnswer());
			preparedStatement.setInt(7, question.getQuestionAnswer());
			preparedStatement.setDouble(8, question.getQuestionMarks());
			preparedStatement.setDouble(9, question.getMarksScored());
			preparedStatement.setLong(10, question.getTestId().longValue());

			int noOfRec = preparedStatement.executeUpdate();
			if (noOfRec == 0) {
				question = null;
			}
		} catch (SQLException e) {
			myLogger.error("Error at update question Dao method: " + e);
		} finally {
			closePreparedStatement();
		}
		return question;
	}

	@Override
	public User saveUser(User user) throws UserException {
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.SAVEUSERSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getUserPassword());
			preparedStatement.setBoolean(3, user.getIsAdmin());
			if (user.getUserTest() != null) {
				preparedStatement.setLong(4, user.getUserTest().getTestId().longValue());
			} else {
				preparedStatement.setNull(4, java.sql.Types.BIGINT);
			}
			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				return null;
			}

			BigInteger generatedId = BigInteger.valueOf(0L);
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = BigInteger.valueOf(resultSet.getLong(1));
			}

			user.setUserId(generatedId);
		} catch (SQLException e) {
			myLogger.error("Error at add User Dao method: " + e);
		} finally {
			closePreparedStatement();
		}
		return user;
	}

	@Override
	public User searchUser(BigInteger userId) throws UserException {
		User user = new User();
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.SEARCHUSERSQL);
			preparedStatement.setLong(1, userId.longValue());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user.setUserId(BigInteger.valueOf(resultSet.getLong("user_id")));
				user.setIsAdmin(resultSet.getBoolean("user_is_admin"));
				user.setUserName(resultSet.getString("user_name"));
				user.setUserPassword(resultSet.getString("user_password"));
				user.setUserTest(searchTest(BigInteger.valueOf(resultSet.getLong(TESTID))));
			} else {
				user = null;
			}
		} catch (SQLException e) {
			myLogger.error("Error at search User Dao method: " + e);
		} finally {
			closePreparedStatement();
		}
		return user;
	}

	@Override
	public User removeUser(BigInteger userId) throws UserException {
		User user = searchUser(userId);
		if (user == null) {
			return user;
		}
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.REMOVEUSERSQL);
			preparedStatement.setLong(1, userId.longValue());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			myLogger.error("Error at remove User Dao method: " + e);
		} finally {
			closePreparedStatement();
		}
		return user;
	}

	@Override
	public User updateUser(User user) throws UserException {
		try {
			preparedStatement = connection.prepareStatement(SqlUtil.UPDATEUSERSQL);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getUserPassword());
			if (user.getUserTest() != null) {
				preparedStatement.setLong(3, user.getUserTest().getTestId().longValue());
			} else {
				preparedStatement.setNull(3, java.sql.Types.BIGINT);
			}
			preparedStatement.setLong(4, user.getUserId().longValue());
			int noOfRec = preparedStatement.executeUpdate();
			if (noOfRec == 0) {
				user = null;
			}
		} catch (SQLException e) {
			myLogger.error("Error at update User Dao method: " + e);
		} finally {
			closePreparedStatement();
		}
		return user;
	}

}
