package com.cg.TestManagement.ui;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import com.cg.TestManagement.Exception.UserException;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;
import com.cg.TestManagement.service.Service;
import com.cg.TestManagement.service.ServiceImpl;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int choice;
		Service service = new ServiceImpl();
		Scanner scanner = new Scanner(System.in);
		do {
			displayOptions();
			System.out.println("Enter your choice:");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the User Id");
				Long user_id = scanner.nextLong();
				try {
					service.validateUserId(user_id);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				System.out.println("Enter the Username");
				scanner.nextLine();
				String user_name = scanner.nextLine();
				try {
					service.validateUserName(user_name);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				System.out.println("Enter the Password");
				String password = scanner.next();
				try {
					service.validatePassword(password);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				System.out.println("Are you an Admin: Enter 'y' for yes");
				String admin = scanner.next();

				User user = new User();
				user.setUserId(user_id);
				user.setUserName(user_name);
				user.setUserPassword(password);
				user.setUserTest(null);
				if (admin.equalsIgnoreCase("y")) {
					user.setIsAdmin(true);
				} else {
					user.setIsAdmin(false);
				}

				User register_user = service.registerUser(user);
				if (register_user != null) {
					System.out.println("User added successfully!!");
				} else {
					System.out.println("User registration failed!!");
				}
				break;

			case 2:
				System.out.println("Enter User Id");
				Long userId_AddTest = scanner.nextLong();
				try {
					service.validateUserId(userId_AddTest);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				User foundUser = service.searchUser(userId_AddTest);
				if (foundUser.getIsAdmin()) {
					System.out.println("Enter OnlineTest Id");
					BigInteger test_id = scanner.nextBigInteger();
					try {
						service.validateTestId(test_id);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Enter OnlineTest Name");
					String test_name = scanner.next();
					System.out.println("Enter OnlineTest Duration:Hours");
					Integer durationHour = scanner.nextInt();
					System.out.println("Enter OnlineTest Duration:Minutes");
					Integer durationMinutes = scanner.nextInt();
					System.out.println("Enter OnlineTest Duration:Seconds");
					Integer durationSeconds = scanner.nextInt();
					System.out.println("Enter OnlineTest Total Marks");
					BigDecimal total_marks = scanner.nextBigDecimal();
					System.out.println("Enter Start Time of OnlineTest:Year");
					Integer startTimeYear = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Month");
					Integer startTimeMonth = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Day");
					Integer startTimeDay = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Hours");
					Integer startTimeHour = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Minutes");
					Integer startTimeMinutes = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Seconds");
					Integer startTimeSeconds = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Year");
					Integer endTimeYear = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Month");
					Integer endTimeMonth = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Day");
					Integer endTimeDay = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Hours");
					Integer endTimeHours = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Minutes");
					Integer endTimeMinutes = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Seconds");
					Integer endTimeSeconds = scanner.nextInt();

					LocalTime duration = LocalTime.of(durationHour, durationMinutes, durationSeconds);
					LocalDateTime startTime = LocalDateTime.of(startTimeYear, startTimeMonth, startTimeDay,
							startTimeHour, startTimeMinutes, startTimeSeconds);
					LocalDateTime endTime = LocalDateTime.of(endTimeYear, endTimeMonth, endTimeDay, endTimeHours,
							endTimeMinutes, endTimeSeconds);
					OnlineTest onlineTest = new OnlineTest();
					onlineTest.setTestId(test_id);
					onlineTest.setTestName(test_name);
					onlineTest.setTestDuration(duration);
					onlineTest.setStartTime(startTime);
					onlineTest.setEndTime(endTime);
					onlineTest.setTestMarksScored(new BigDecimal(0.0));
					try {
						service.validateDate(startTime, endTime);
						service.validateEndTime(endTime);
						service.validateTestDuration(duration, startTime, endTime);
					} catch (UserException e) {
						System.out.println(e.getMessage());
						break;
					}
					onlineTest.setTestTotalMarks(total_marks);

					OnlineTest addTest = service.addTest(onlineTest);
					if (addTest != null) {
						System.out.println("OnlineTest added successfully!");
					} else {
						System.out.println("OnlineTest could not be added");
					}
				} else {
					System.out.println("Not allowed to perform this action");
				}
				break;

			case 3:
				System.out.println("Enter User Id");
				Long userId_UpdateTest = scanner.nextLong();
				try {
					service.validateUserId(userId_UpdateTest);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				User updateTestUser = service.searchUser(userId_UpdateTest);
				if (updateTestUser.getIsAdmin()) {
					System.out.println("Enter the OnlineTest Id to be updated");
					BigInteger updateTestId = scanner.nextBigInteger();
					try {
						service.validateTestId(updateTestId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Enter OnlineTest Name");
					String test_name = scanner.next();
					System.out.println("Enter OnlineTest Duration:Hours");
					Integer durationHour = scanner.nextInt();
					System.out.println("Enter OnlineTest Duration:Minutes");
					Integer durationMinutes = scanner.nextInt();
					System.out.println("Enter OnlineTest Duration:Seconds");
					Integer durationSeconds = scanner.nextInt();
					System.out.println("Enter OnlineTest Total Marks");
					BigDecimal total_marks = scanner.nextBigDecimal();
					System.out.println("Enter Start Time of OnlineTest:Year");
					Integer startTimeYear = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Month");
					Integer startTimeMonth = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Day");
					Integer startTimeDay = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Hours");
					Integer startTimeHour = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Minutes");
					Integer startTimeMinutes = scanner.nextInt();
					System.out.println("Enter Start Time of OnlineTest:Seconds");
					Integer startTimeSeconds = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Year");
					Integer endTimeYear = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Month");
					Integer endTimeMonth = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Day");
					Integer endTimeDay = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Hours");
					Integer endTimeHours = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Minutes");
					Integer endTimeMinutes = scanner.nextInt();
					System.out.println("Enter End Time of OnlineTest:Seconds");
					Integer endTimeSeconds = scanner.nextInt();

					LocalTime duration = LocalTime.of(durationHour, durationMinutes, durationSeconds);
					LocalDateTime startTime = LocalDateTime.of(startTimeYear, startTimeMonth, startTimeDay,
							startTimeHour, startTimeMinutes, startTimeSeconds);
					LocalDateTime endTime = LocalDateTime.of(endTimeYear, endTimeMonth, endTimeDay, endTimeHours,
							endTimeMinutes, endTimeSeconds);
					OnlineTest onlineTest = new OnlineTest();
					onlineTest.setTestId(updateTestId);
					onlineTest.setTestName(test_name);
					onlineTest.setTestDuration(duration);
					onlineTest.setStartTime(startTime);
					onlineTest.setEndTime(endTime);
					onlineTest.setTestMarksScored(new BigDecimal(0.0));

					try {
						service.validateDate(startTime, endTime);
						service.validateEndTime(endTime);
						service.validateTestDuration(duration, startTime, endTime);
					} catch (UserException e) {
						System.out.println(e.getMessage());
						break;
					}
					onlineTest.setTestTotalMarks(total_marks);

					OnlineTest updatedTest;
					try {
						updatedTest = service.updateTest(updateTestId, onlineTest);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					if (updatedTest != null) {
						System.out.println("OnlineTest Updated Successfully!");
					} else {
						System.out.println("OnlineTest cannot be Updated!");
					}
				} else {
					System.out.println("Not allowed to perform this action");
				}
				break;

			case 4:
				System.out.println("Enter User Id");
				Long userId_DeleteTest = scanner.nextLong();
				try {
					service.validateUserId(userId_DeleteTest);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				User deleteTestUser = service.searchUser(userId_DeleteTest);
				if (deleteTestUser.getIsAdmin()) {
					System.out.println("Enter the OnlineTest Id to be deleted");
					BigInteger deleteTestId = scanner.nextBigInteger();
					try {
						service.validateTestId(deleteTestId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					OnlineTest deletedTest = service.deleteTest(deleteTestId);
					if (deletedTest != null) {
						System.out.println("OnlineTest Deleted Successfully!");
					} else {
						System.out.println("OnlineTest could not be Deleted!");
					}
				} else {
					System.out.println("Not allowed to perform this action");
				}
				break;

			case 5:
				System.out.println("Enter User Id");
				Long user_Id = scanner.nextLong();
				try {
					service.validateUserId(user_Id);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				User assignUser = service.searchUser(user_Id);
				if (assignUser.getIsAdmin()) {
					System.out.println("Enter the User Id to whom test is to be alloted");
					Long userTestId = scanner.nextLong();
					try {
						service.validateUserId(userTestId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Enter the OnlineTest Id of the test to be alloted");
					BigInteger testUserId = scanner.nextBigInteger();
					try {
						service.validateTestId(testUserId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					Boolean assign;
					try {
						assign = service.assignTest(userTestId, testUserId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					if (assign) {
						System.out.println("OnlineTest assigned to User Successfully!");
					} else {
						System.out.println("OnlineTest could not be assigned to the User");
					}
				} else {
					System.out.println("Not allowed to perform this action");
				}
				break;

			case 6:
				System.out.println("Enter User Id");
				Long userId_AddQuestion = scanner.nextLong();
				try {
					service.validateUserId(userId_AddQuestion);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				User addQuestionUser = service.searchUser(userId_AddQuestion);
				if (addQuestionUser.getIsAdmin()) {
					System.out.println("Enter OnlineTest Id to which question is to be added");
					BigInteger questionTestId = scanner.nextBigInteger();
					try {
						service.validateTestId(questionTestId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Enter the Question Id to be added");
					BigInteger questionId = scanner.nextBigInteger();
					try {
						service.validateQuestionId(questionId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Enter Question Title");
					scanner.nextLine();
					String questionTitle = scanner.nextLine();
					System.out.println("Enter Question Options");
					String questionOptions[] = new String[4];
					for (int i = 0; i < 4; i++) {
						questionOptions[i] = scanner.next();
					}
					scanner.nextLine();
					System.out.println("Enter the Question Answer");
					Integer questionAnswer = scanner.nextInt();
					try {
						service.questionAnswerValidate(questionAnswer);
					} catch (UserException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}
					System.out.println("Enter the Question Marks");
					BigDecimal questionMarks = scanner.nextBigDecimal();

					Question question = new Question();
					question.setQuestionId(questionId);
					question.setQusetionOptions(questionOptions);
					question.setQuestionTitle(questionTitle);
					question.setQuestionAnswer(questionAnswer - 1);
					question.setQuestionMarks(questionMarks);

					Question addQuestion;
					try {
						addQuestion = service.addQuestion(questionTestId, question);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					if (addQuestion != null) {
						System.out.println("Question added successfully!");
					} else {
						System.out.println("Question could not be added!");
					}
				} else {
					System.out.println("Not allowed to perform this action");
				}
				break;

			case 7:
				System.out.println("Enter User Id");
				Long userId_UpdateQuestion = scanner.nextLong();
				try {
					service.validateUserId(userId_UpdateQuestion);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				User updateQuestionUser = service.searchUser(userId_UpdateQuestion);
				if (updateQuestionUser.getIsAdmin()) {
					System.out.println("Enter the OnlineTest Id to be updated");
					BigInteger updateTestQuestionId = scanner.nextBigInteger();
					try {
						service.validateTestId(updateTestQuestionId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Enter Question Id to be updated");
					BigInteger updateQuestionId = scanner.nextBigInteger();
					try {
						service.validateQuestionId(updateQuestionId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Enter the updated Question Id");
					BigInteger updatedQuestionId = scanner.nextBigInteger();
					try {
						service.validateQuestionId(updatedQuestionId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Enter the updated Question Title");
					scanner.nextLine();
					String updatedQuestionTitle = scanner.nextLine();
					System.out.println("Enter the updated Question Options");
					String updatedQuestionOptions[] = new String[4];
					for (int i = 0; i < 4; i++) {
						updatedQuestionOptions[i] = scanner.next();
					}
					System.out.println("Enter the updated Question Answer");
					Integer updatedQuestionAnswer = scanner.nextInt();
					try {
						service.questionAnswerValidate(updatedQuestionAnswer);
					} catch (UserException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}
					System.out.println("Enter the updated Question Marks");
					BigDecimal updatedQuestionMarks = scanner.nextBigDecimal();
					Question question = new Question();
					question.setQuestionId(updatedQuestionId);
					question.setQusetionOptions(updatedQuestionOptions);
					question.setQuestionTitle(updatedQuestionTitle);
					question.setQuestionAnswer(updatedQuestionAnswer - 1);
					question.setQuestionMarks(updatedQuestionMarks);
					Question updatedQuestion;
					try {
						updatedQuestion = service.updateQuestion(updateTestQuestionId, updateQuestionId, question);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					if (updatedQuestion != null) {
						System.out.println("Question Updated Successfully!");
					} else {
						System.out.println("Question could not be Updated!");
					}
				} else {
					System.out.println("Not allowed to perform this action");
				}
				break;

			case 8:
				System.out.println("Enter User Id");
				Long userId_DeleteQuestion = scanner.nextLong();
				try {
					service.validateUserId(userId_DeleteQuestion);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				User deleteQuestionUser = service.searchUser(userId_DeleteQuestion);
				if (deleteQuestionUser.getIsAdmin()) {
					System.out.println("Enter the OnlineTest Id from which Question is to be deleted");
					BigInteger deleteTestQuestionId = scanner.nextBigInteger();
					try {
						service.validateTestId(deleteTestQuestionId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Enter Question Id to be deleted");
					BigInteger deleteQuestionId = scanner.nextBigInteger();
					try {
						service.validateQuestionId(deleteQuestionId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					Question deletedQuestion;
					try {
						deletedQuestion = service.deleteQuestion(deleteTestQuestionId, deleteQuestionId);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
					if (deletedQuestion != null) {
						System.out.println("Question Deleted Successfully!");
					} else {
						System.out.println("Question could not be Deleted!");
					}
				} else {
					System.out.println("Not allowed to perform this action");
				}
				break;

			case 9:
				System.out.println("Enter OnlineTest Id for which the result is to be checked");
				BigInteger resultTestId = scanner.nextBigInteger();
				try {
					service.validateTestId(resultTestId);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				OnlineTest resultTest = service.searchTest(resultTestId);
				BigDecimal marksScored = service.getResult(resultTest);
				System.out.println("The Marks Scored are " + marksScored);
				break;

			case 10:
				Map<Long, User> userDatabase = service.showUsers();
				for (Map.Entry<Long, User> entry : userDatabase.entrySet()) {
					System.out.println(entry.getKey() + " : " + entry.getValue().toString());
				}
				break;

			case 11:
				Map<BigInteger, OnlineTest> testDatabase = service.showTests();
				for (Map.Entry<BigInteger, OnlineTest> entry : testDatabase.entrySet()) {
					System.out.println(entry.getKey() + " : " + entry.getValue().toString());
				}
				break;

			case 12:
				Map<BigInteger, Question> questionDatabase = service.showQuestions();
				for (Map.Entry<BigInteger, Question> entry : questionDatabase.entrySet()) {
					System.out.println(entry.getKey() + " : " + entry.getValue().toString());
				}
				break;

			case 13:
				System.out.println("Enter User Id");
				Long userId_AnswerQuestion = scanner.nextLong();
				try {
					service.validateUserId(userId_AnswerQuestion);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				User answerUser = service.searchUser(userId_AnswerQuestion);
				if (answerUser != null && !answerUser.getIsAdmin() && answerUser.getUserTest() != null) {
					Set<Question> questions = answerUser.getUserTest().getTestQuestions();
					for (Question question : questions) {
						System.out.println(question);
					}
					System.out.println("Choose the question");
					BigInteger qid = scanner.nextBigInteger();
					System.out.println("Choose Answer");
					Integer answer = scanner.nextInt();
					try {
						service.answerQuestion(answerUser.getUserTest(),
								service.showQuestion(answerUser.getUserTest(), qid), answer - 1);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						break;
					}
				} else {
					System.out.println("Cannot answer question!");
				}
				break;

			case 14:
				System.exit(0);
				break;

			default:
				System.out.println("Please enter a choice between 1 and 14 only");
				break;

			}
		} while (choice != 14);
		scanner.close();

	}

	public static void displayOptions() {
		System.out.println("The available actions are:");
		System.out.println("1 to Register as a User");
		System.out.println("2 to Add OnlineTest");
		System.out.println("3 to Update OnlineTest");
		System.out.println("4 to Delete OnlineTest");
		System.out.println("5 to Assign test to a User");
		System.out.println("6 to Add Questions");
		System.out.println("7 to Update Questions");
		System.out.println("8 to Delete Questions");
		System.out.println("9 to Check Result");
		System.out.println("10 to Display all Users");
		System.out.println("11 to Display all Tests");
		System.out.println("12 to Display all Questions");
		System.out.println("13 to Answer a question");
		System.out.println("14 to Exit");
	}
}
