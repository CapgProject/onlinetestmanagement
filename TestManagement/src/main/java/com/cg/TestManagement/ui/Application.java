package com.cg.TestManagement.ui;

import java.math.BigInteger;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.TestManagement.exception.ExceptionMessage;
import com.cg.TestManagement.exception.UserException;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;
import com.cg.TestManagement.service.Service;
import com.cg.TestManagement.service.ServiceImpl;

public class Application {
	private static Scanner scanner;
	static Service service;
	private static final String LINE = "------------------------------------------------------------------------------";
	private static Logger myLogger;
	static {

		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("DbUtil.class");
	}

	static {
		scanner = new Scanner(System.in);
		service = new ServiceImpl();
	}

	public static final String DURATIONMESSAGE = "HH:MM:SS";
	public static final String DATEMESSAGE = "dd-MM-yyyy HH:mm:ss";
	public static final String CHOICEMESSAGE = "Enter choice between 1 and 4 to perform available actions:";
	public static final String GOBACKMESSAGE = "4.Go Back";
	public static final String INVALIDENTRYMESSAGE = "Please enter a choice between 1 and 4 only!";
	public static final String INPUTUSERID = "Enter User Id";

	private Application() {
		super();
	}

	public static void main(String[] args) {

		int choice = 0;
		do {
			System.out.println(LINE);
			System.out.println("Enter your choice between 1 and 4");
			System.out.println(LINE);
			System.out.println("1.Admin");
			System.out.println("2.User");
			System.out.println("3.Update Profile");
			System.out.println("4.Exit");
			System.out.println(LINE);
			try {
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					int adminChoice = 0;
					displayAdminOptions(adminChoice);
					break;
				case 2:
					int userChoice = 0;
					displayUserOptions(userChoice);
					break;
				case 3:
					updateUserProfile();
					break;
				case 4:
					choice = exitApplication();
					break;
				default:
					System.out.println(INVALIDENTRYMESSAGE);
					break;
				}
			} catch (InputMismatchException e) {
				myLogger.error(e);
				System.out.println(ExceptionMessage.INVALIDINPUTMESSAGE);
				scanner.nextLine();
			}
		} while (choice != 4);
		scanner.close();
	}

	public static void displayAdminOptions(int adminChoice) {
		do {
			System.out.println(LINE);
			System.out.println(CHOICEMESSAGE);
			System.out.println(LINE);
			System.out.println("1.Perform Actions on Test");
			System.out.println("2.Perform Actions on Question");
			System.out.println("3.Assign Test to a User");
			System.out.println(GOBACKMESSAGE);
			System.out.println(LINE);
			try {
				adminChoice = scanner.nextInt();
				switch (adminChoice) {
				case 1:
					int testChoice = 0;
					displayAdminTestActions(testChoice);
					break;
				case 2:
					int questionChoice = 0;
					displayAdminQuestionActions(questionChoice);
					break;
				case 3:
					assignTestToUser();
					break;
				case 4:
					break;
				default:
					System.out.println(INVALIDENTRYMESSAGE);
					break;
				}
			} catch (InputMismatchException e) {
				myLogger.error(e);
				System.out.println(ExceptionMessage.INVALIDINPUTMESSAGE);
				scanner.nextLine();
			}
		} while (adminChoice != 4);
	}

	public static void displayAdminTestActions(int testChoice) {
		while (testChoice != 4) {
			System.out.println(LINE);
			System.out.println(CHOICEMESSAGE);
			System.out.println(LINE);
			System.out.println("1.Add Test");
			System.out.println("2.Update Test");
			System.out.println("3.Delete Test");
			System.out.println(GOBACKMESSAGE);
			System.out.println(LINE);
			try {
				testChoice = scanner.nextInt();
				switch (testChoice) {
				case 1:
					addTest();
					break;
				case 2:
					updateTest();
					break;
				case 3:
					deleteTest();
					break;
				case 4:
					break;
				default:
					System.out.println(INVALIDENTRYMESSAGE);
					break;
				}
			} catch (InputMismatchException e) {
				myLogger.error(e);
				System.out.println(ExceptionMessage.INVALIDINPUTMESSAGE);
				scanner.nextLine();
			}
		}
	}

	public static void displayAdminQuestionActions(int questionChoice) {
		do {
			System.out.println(LINE);
			System.out.println(CHOICEMESSAGE);
			System.out.println(LINE);
			System.out.println("1.Add Question");
			System.out.println("2.Update Question");
			System.out.println("3.Delete Question");
			System.out.println(GOBACKMESSAGE);
			System.out.println(LINE);
			try {
				questionChoice = scanner.nextInt();
				switch (questionChoice) {
				case 1:
					addQuestion();
					break;
				case 2:

					updateQuestion();

					break;
				case 3:
					deleteQuestion();
					break;
				case 4:
					break;
				default:
					System.out.println(INVALIDENTRYMESSAGE);
					break;
				}
			} catch (InputMismatchException e) {
				myLogger.error(e);
				System.out.println(ExceptionMessage.INVALIDINPUTMESSAGE);
				scanner.nextLine();
			}
		} while (questionChoice != 4);
	}

	public static void displayUserOptions(int userChoice) {
		do {
			System.out.println(LINE);
			System.out.println(CHOICEMESSAGE);
			System.out.println(LINE);
			System.out.println("1.Register User");
			System.out.println("2.Give Test");
			System.out.println("3.Check Result");
			System.out.println(GOBACKMESSAGE);
			System.out.println(LINE);
			try {
				userChoice = scanner.nextInt();
				switch (userChoice) {
				case 1:
					registerUser();
					break;
				case 2:
					giveTest();
					break;
				case 3:
					checkResult();
					break;
				case 4:
					break;
				default:
					System.out.println(INVALIDENTRYMESSAGE);
					break;
				}
			} catch (InputMismatchException e) {
				myLogger.error(e);
				System.out.println(ExceptionMessage.INVALIDINPUTMESSAGE);
				scanner.nextLine();
			}
		} while (userChoice != 4);
	}

	public static int exitApplication() {
		System.out.println("Do you really want to exit? Enter 'y' to exit");
		String exitChoice = scanner.next();
		if ("y".equalsIgnoreCase(exitChoice)) {
			System.out.println("Thank You for using our Application!!");
			return 4;
		} else {
			return 0;
		}
	}

	public static void addTest() {
		System.out.println(INPUTUSERID);
		try {
			BigInteger userId_AddTest = scanner.nextBigInteger();

			service.validateUserId(userId_AddTest);

			User foundUser = service.searchUser(userId_AddTest);
			if (foundUser != null && foundUser.getIsAdmin()) {
				OnlineTest onlineTest = inputTest();
				OnlineTest addTest = service.addTest(onlineTest);
				if (addTest != null) {
					System.out.println("OnlineTest added successfully!");
				} else {
					System.out.println("OnlineTest could not be added");
				}

			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}
	}

	public static void catchBlock(Exception e) {
		if (e.getClass() == InputMismatchException.class){
			myLogger.error(e);
			System.err.println(ExceptionMessage.INVALIDINPUTMESSAGE);
		}
		else if(e.getClass() == UserException.class) {
			myLogger.error(e);
			System.err.println(e.getMessage());
		}
		else {
			myLogger.error(e);
			System.err.println(ExceptionMessage.INVALIDDATEMESSAGE);				
		}
		scanner.nextLine();	
	}
	
	public static void updateTest() {
		System.out.println(INPUTUSERID);
		try {
			BigInteger userId_UpdateTest = scanner.nextBigInteger();
			service.validateUserId(userId_UpdateTest);
			User updateTestUser;
			updateTestUser = service.searchUser(userId_UpdateTest);

			if (updateTestUser != null && updateTestUser.getIsAdmin()) {
				System.out.println("Enter the OnlineTest Id to be updated");
				BigInteger updateTestId = scanner.nextBigInteger();

				service.validateTestId(updateTestId);

				OnlineTest onlineTest = inputTest();
				onlineTest.setTestId(updateTestId);
				OnlineTest updatedTest;

				updatedTest = service.updateTest(updateTestId, onlineTest);
				if (updatedTest != null) {
					System.out.println("OnlineTest Updated Successfully!");
				} else {
					System.out.println("OnlineTest cannot be Updated!");
				}

			} else {
				System.out.println("Not allowed to perform this action");
			}

		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}
	}

	public static void deleteTest() {
		System.out.println(INPUTUSERID);
		try {
			BigInteger userId_DeleteTest = scanner.nextBigInteger();

			service.validateUserId(userId_DeleteTest);

			User deleteTestUser = service.searchUser(userId_DeleteTest);
			if (deleteTestUser != null && deleteTestUser.getIsAdmin()) {
				System.out.println("Enter the OnlineTest Id to be deleted");
				BigInteger deleteTestId = scanner.nextBigInteger();

				service.validateTestId(deleteTestId);

				OnlineTest deletedTest = service.deleteTest(deleteTestId);
				if (deletedTest != null) {
					System.out.println("OnlineTest Deleted Successfully!");
				} else {
					System.out.println("OnlineTest could not be Deleted!");
				}
			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException e) {
			myLogger.error(e);
			System.err.println(ExceptionMessage.INVALIDINPUTMESSAGE);
			scanner.nextLine();
		} catch (UserException e) {
			myLogger.error(e);
			System.err.println(e.getMessage());
			scanner.nextLine();
		} catch (Exception e) {
			myLogger.error(e);
			System.err.println(ExceptionMessage.INVALIDDATEMESSAGE);
			scanner.nextLine();
		}
	}

	public static void assignTestToUser() {
		System.out.println(INPUTUSERID);
		try {
			BigInteger user_Id = scanner.nextBigInteger();
			service.validateUserId(user_Id);
			User assignUser;
			assignUser = service.searchUser(user_Id);
			if (assignUser.getIsAdmin()) {
				System.out.println("Enter the User Id to whom test is to be assigned");
				BigInteger userTestId = scanner.nextBigInteger();
				service.validateUserId(userTestId);
				System.out.println("Enter the OnlineTest Id of the test to be alloted");
				BigInteger testUserId = scanner.nextBigInteger();
				service.validateTestId(testUserId);
				Boolean assign;
				OnlineTest test = service.searchTest(testUserId);
				if (test.getIsTestAssigned() == null) {
					test.setIsTestAssigned(false);
				}
				assign = service.assignTest(userTestId, testUserId);
				if (assign) {
					System.out.println("OnlineTest assigned to User Successfully!");
				} else {
					System.out.println("OnlineTest could not be assigned to the User");
				}
			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}
	}

	public static void addQuestion() {
		System.out.println(INPUTUSERID);
		try {
			BigInteger userId_AddQuestion = scanner.nextBigInteger();

			service.validateUserId(userId_AddQuestion);

			User addQuestionUser = service.searchUser(userId_AddQuestion);
			if (addQuestionUser != null && addQuestionUser.getIsAdmin()) {
				System.out.println("Enter OnlineTest Id to which question is to be added");
				BigInteger questionTestId = scanner.nextBigInteger();

				service.validateTestId(questionTestId);

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

				service.questionAnswerValidate(questionAnswer);

				System.out.println("Enter the Question Marks");
				Double questionMarks = scanner.nextDouble();

				Question question = new Question();
				question.setQuestionOptions(questionOptions);
				question.setQuestionTitle(questionTitle);
				question.setQuestionAnswer(questionAnswer - 1);
				question.setQuestionMarks(questionMarks);

				Question addQuestion;

				addQuestion = service.addQuestion(questionTestId, question);
				if (addQuestion != null) {
					System.out.println("Question added successfully!");
				} else {
					System.out.println("Question could not be added!");
				}

			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}
	}

	public static void updateQuestion() {
		System.out.println(INPUTUSERID);
		try {
			BigInteger userId_UpdateQuestion = scanner.nextBigInteger();

			service.validateUserId(userId_UpdateQuestion);

			User updateQuestionUser = service.searchUser(userId_UpdateQuestion);
			if (updateQuestionUser != null && updateQuestionUser.getIsAdmin()) {
				System.out.println("Enter the OnlineTest Id to be updated");
				BigInteger updateTestQuestionId = scanner.nextBigInteger();

				service.validateTestId(updateTestQuestionId);

				System.out.println("Enter Question Id to be updated");
				BigInteger updateQuestionId = scanner.nextBigInteger();

				service.validateQuestionId(updateQuestionId);

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

				service.questionAnswerValidate(updatedQuestionAnswer);

				System.out.println("Enter the updated Question Marks");
				Double updatedQuestionMarks = scanner.nextDouble();
				Question question = new Question();
				question.setQuestionOptions(updatedQuestionOptions);
				question.setQuestionTitle(updatedQuestionTitle);
				question.setQuestionAnswer(updatedQuestionAnswer - 1);
				question.setQuestionMarks(updatedQuestionMarks);
				Question updatedQuestion;

				updatedQuestion = service.updateQuestion(updateTestQuestionId, updateQuestionId, question);
				if (updatedQuestion != null) {
					System.out.println("Question Updated Successfully!");
				} else {
					System.out.println("Question could not be Updated!");
				}

			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}
	}

	public static void deleteQuestion() {
		System.out.println(INPUTUSERID);
		try {
			BigInteger userId_DeleteQuestion = scanner.nextBigInteger();

			service.validateUserId(userId_DeleteQuestion);

			User deleteQuestionUser = service.searchUser(userId_DeleteQuestion);
			if (deleteQuestionUser != null && deleteQuestionUser.getIsAdmin()) {
				System.out.println("Enter the OnlineTest Id from which Question is to be deleted");
				BigInteger deleteTestQuestionId = scanner.nextBigInteger();

				service.validateTestId(deleteTestQuestionId);

				System.out.println("Enter Question Id to be deleted");
				BigInteger deleteQuestionId = scanner.nextBigInteger();

				service.validateQuestionId(deleteQuestionId);

				Question deletedQuestion;

				deletedQuestion = service.deleteQuestion(deleteTestQuestionId, deleteQuestionId);
				if (deletedQuestion != null) {
					System.out.println("Question Deleted Successfully!");
				} else {
					System.out.println("Question could not be Deleted!");
				}

			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}
	}

	public static void registerUser() {
		try {
			System.out.println("Enter the Username");
			scanner.nextLine();
			String user_name = scanner.nextLine();

			service.validateUserName(user_name);

			System.out.println("Enter the Password");
			String password = scanner.next();

			service.validatePassword(password);

			User user = new User();
			user.setUserName(user_name);
			user.setUserPassword(password);
			user.setUserTest(null);
			user.setIsAdmin(false);

			service.registerUser(user);
			System.out.println("User added successfully!");

		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}
	}

	public static void giveTest() {
		System.out.println(INPUTUSERID);
		try {
			BigInteger userId_AnswerQuestion = scanner.nextBigInteger();

			service.validateUserId(userId_AnswerQuestion);

			User answerUser = service.searchUser(userId_AnswerQuestion);
			if (answerUser != null && !answerUser.getIsAdmin() && answerUser.getUserTest() != null) {
				Set<Question> questions = answerUser.getUserTest().getTestQuestions();
				for (Question question : questions) {
					System.out.println(question.getQuestionTitle());
					System.out.println(Arrays.toString(question.getQuestionOptions()));
					BigInteger qid = question.getQuestionId();
					System.out.println("Choose Answer");
					Integer answer = scanner.nextInt();

					service.answerQuestion(answerUser.getUserTest(),
							service.showQuestion(answerUser.getUserTest(), qid), answer - 1);

				}
			} else {
				System.out.println("Cannot answer question!");
			}
		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}

	}

	public static void checkResult() {
		System.out.println("Enter OnlineTest Id for which the result is to be checked");
		try {
			BigInteger resultTestId = scanner.nextBigInteger();
			service.validateTestId(resultTestId);

			OnlineTest resultTest = service.searchTest(resultTestId);
			if (resultTest != null) {
				Double marksScored = service.getResult(resultTest);
				System.out.println("The Marks Scored are " + marksScored);
			} else {
				System.out.println("The test does not exist!");
			}
		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}
	}

	public static void updateUserProfile() {
		User updateUser;
		try {
			System.out.println("Enter your User Id");
			BigInteger id = scanner.nextBigInteger();

			updateUser = service.searchUser(id);
			System.out.println("Enter the updated username");
			String updatedUsername = scanner.next();
			service.validateUserName(updatedUsername);
			updateUser.setUserName(updatedUsername);
			System.out.println("Do you want to change your password?");
			System.out.println("Enter 'y' to update password");
			String choice = scanner.next();
			if ("y".equalsIgnoreCase(choice)) {
				System.out.println("Enter new password");
				String updatedPassword = scanner.next();
				service.validatePassword(updatedPassword);
				updateUser.setUserPassword(updatedPassword);
			}
			service.updateProfile(updateUser);
			System.out.println("User updated succesfully!");

		} catch (InputMismatchException | UserException | DateTimeException e) {
			catchBlock(e);
		}

	}

	private static OnlineTest inputTest() throws UserException, InputMismatchException {
		System.out.println("Enter OnlineTest Name");
		String test_name = scanner.next();
		System.out.println("Enter Test Duration in the format : " + DURATIONMESSAGE);
		String durationPattern = scanner.next();
		DateTimeFormatter durationFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime duration = LocalTime.parse(durationPattern, durationFormatter);
		scanner.nextLine();
		System.out.println("Enter Start Time in the Format : " + DATEMESSAGE);
		String startPattern = scanner.nextLine();
		DateTimeFormatter startTimeFormatter = DateTimeFormatter.ofPattern(DATEMESSAGE);
		LocalDateTime startTime = LocalDateTime.parse(startPattern, startTimeFormatter);
		System.out.println("Enter End Time in the Format : " + DATEMESSAGE);
		String endPattern = scanner.nextLine();
		DateTimeFormatter endTimeFormatter = DateTimeFormatter.ofPattern(DATEMESSAGE);
		LocalDateTime endTime = LocalDateTime.parse(endPattern, endTimeFormatter);
		service.validateDate(startTime, endTime);
		service.validateEndTime(endTime);
		service.validateTestDuration(duration, startTime, endTime);
		OnlineTest onlineTest = new OnlineTest();
		onlineTest.setTestName(test_name);
		onlineTest.setTestDuration(duration);
		onlineTest.setStartTime(startTime);
		onlineTest.setEndTime(endTime);
		onlineTest.setTestMarksScored(0.0);
		onlineTest.setTestTotalMarks(0.0);
		return onlineTest;
	}
}