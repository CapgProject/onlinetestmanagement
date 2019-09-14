package com.cg.TestManagement.ui;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.cg.TestManagement.Exception.ExceptionMessage;
import com.cg.TestManagement.Exception.UserException;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;
import com.cg.TestManagement.service.Service;
import com.cg.TestManagement.service.ServiceImpl;

public class Application {
	private static Scanner scanner;
	static Service service;

	static {
		scanner = new Scanner(System.in);
		service = new ServiceImpl();
	}

	public static void main(String[] args) {

		int choice = 0;
		do {
			System.out.println("Enter your choice");
			System.out.println("1.Admin\t\t\t2.User\t\t\t3.Exit");
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
					System.exit(0);
					break;

				default:
					System.out.println("Enter a choice between 1 and 3 only!");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number!");
				scanner.nextLine();
			}
		} while (choice != 3);
		scanner.close();
	}

	public static void displayAdminOptions(int adminChoice){
		do {
			System.out.println("The available actions are :");
			System.out.println("Enter 1 to perform actions on Test");
			System.out.println("Enter 2 to perform actions on Question");
			System.out.println("Enter 3 to Assign Test to a User");
			System.out.println("Enter 4 to Go Back");

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
					System.out.println("Please enter a choice between 1 and 4 only!");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number!");
				scanner.nextLine();
			}
		} while (adminChoice != 4);
	}

	public static void displayAdminTestActions(int testChoice) {
		do {
			System.out.println("Enter 1 to Add Test");
			System.out.println("Enter 2 to Update Test");
			System.out.println("Enter 3 to Delete Test");
			System.out.println("Enter 4 to Go Back");
			try {
				testChoice = scanner.nextInt();
				switch (testChoice) {
				case 1:
					try {
						addTest();
					} catch (UserException e) {
						System.out.println(e.getMessage());
					}break;
				case 2:
					try {
						updateTest();
					} catch (UserException e) {
						System.out.println(e.getMessage());
					}break;
				case 3:
					try {
						deleteTest();
					} catch (UserException e) {
						System.out.println(e.getMessage());
					}break;
				case 4:
					break;
				default:
					System.out.println("Please enter a choice between 1 and 4 only!");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input Type!");
				scanner.nextLine();
			}
		} while (testChoice != 4);
	}

	public static void displayAdminQuestionActions(int questionChoice) {
		do {
			System.out.println("Enter 1 to Add Question");
			System.out.println("Enter 2 to Update Question");
			System.out.println("Enter 3 to Delete Question");
			System.out.println("Enter 4 to Go Back");
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
					System.out.println("Please enter a choice between 1 and 4 only!");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input Type!");
				scanner.nextLine();
			}
		} while (questionChoice != 4);
	}

	public static void displayUserOptions(int userChoice) {
		do {
			System.out.println("The available options are :");
			System.out.println("1 to Register User");
			System.out.println("2 to Give Test");
			System.out.println("3 to Check Result");
			System.out.println("4 to Go Back");

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
					System.out.println("Please enter a choice between 1 and 4 only!");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number!");
				scanner.nextLine();
			}
		} while (userChoice != 4);
	}

	public static void addTest() throws UserException {
		System.out.println("Enter User Id");
		try {
			Long userId_AddTest = scanner.nextLong();
			try {
				service.validateUserId(userId_AddTest);
			} catch (UserException e) {
				//System.out.println(e.getMessage());
				throw new UserException(e.getMessage());
			}
			User foundUser = service.searchUser(userId_AddTest);
			if (foundUser.getIsAdmin()) {
				System.out.println("Enter OnlineTest Id");
				BigInteger test_id = scanner.nextBigInteger();
				try {
					service.validateTestId(test_id);
				} catch (UserException e) {
					//System.out.println(e.getMessage());
					throw new UserException(e.getMessage());
				}
				System.out.println("Enter OnlineTest Name");
				String test_name = scanner.next();
				System.out.println("Enter Test Duration in the format : HH:MM:SS");
				try {
					String durationPattern = scanner.next();
					DateTimeFormatter durationFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
					LocalTime duration = LocalTime.parse(durationPattern, durationFormatter);
					System.out.println("Enter OnlineTest Total Marks");
					Double total_marks = scanner.nextDouble();
					scanner.nextLine();
					System.out.println("Enter Start Time in the Format : DD-MM-YYYY HH:MM:SS");
					String startPattern = scanner.nextLine();
					DateTimeFormatter startTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					LocalDateTime startTime = LocalDateTime.parse(startPattern, startTimeFormatter);
					System.out.println("Enter End Time in the Format : DD-MM-YYYY HH:MM:SS");
					String endPattern = scanner.nextLine();
					DateTimeFormatter endTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					LocalDateTime endTime = LocalDateTime.parse(endPattern, endTimeFormatter);

					service.validateDate(startTime, endTime);
					service.validateEndTime(endTime);
					service.validateTestDuration(duration, startTime, endTime);
					OnlineTest onlineTest = new OnlineTest();
					onlineTest.setTestId(test_id);
					onlineTest.setTestName(test_name);
					onlineTest.setTestDuration(duration);
					onlineTest.setStartTime(startTime);
					onlineTest.setEndTime(endTime);
					onlineTest.setTestMarksScored(0.0);
					onlineTest.setTestTotalMarks(total_marks);
					OnlineTest addTest = service.addTest(onlineTest);
					if (addTest != null) {
						System.out.println("OnlineTest added successfully!");
					} else {
						System.out.println("OnlineTest could not be added");
					}
				} catch (UserException e) {
					//System.out.println(e.getMessage());
					throw new UserException(e.getMessage());
				} catch (Exception e) {
					//System.out.println("Input entered in wrong format!");
					throw new UserException(e.getMessage());
				}

			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Type!");
		}
	}

	public static void updateTest() throws UserException {
		System.out.println("Enter User Id");
		try {
			Long userId_UpdateTest = scanner.nextLong();
			try {
				service.validateUserId(userId_UpdateTest);
			} catch (UserException e) {
				//System.out.println(e.getMessage());
				throw new UserException(e.getMessage());
			}
			User updateTestUser = service.searchUser(userId_UpdateTest);
			if (updateTestUser.getIsAdmin()) {
				System.out.println("Enter the OnlineTest Id to be updated");
				BigInteger updateTestId = scanner.nextBigInteger();
				try {
					service.validateTestId(updateTestId);
				} catch (UserException e) {
					//System.out.println(e.getMessage());
					throw new UserException(e.getMessage());
				}
				System.out.println("Enter OnlineTest Name");
				String test_name = scanner.next();
				try {
					System.out.println("Enter Test Duration in the format : HH:MM:SS");
					String durationPattern = scanner.next();
					DateTimeFormatter durationFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
					LocalTime duration = LocalTime.parse(durationPattern, durationFormatter);
					System.out.println("Enter OnlineTest Total Marks");
					Double total_marks = scanner.nextDouble();
					System.out.println("Enter Start Time in the Format : DD-MM-YYYY HH:MM:SS");
					String startPattern = scanner.nextLine();
					DateTimeFormatter startTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					LocalDateTime startTime = LocalDateTime.parse(startPattern, startTimeFormatter);
					System.out.println("Enter End Time in the Format : DD-MM-YYYY HH:MM:SS");
					String endPattern = scanner.nextLine();
					DateTimeFormatter endTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					LocalDateTime endTime = LocalDateTime.parse(endPattern, endTimeFormatter);

					service.validateDate(startTime, endTime);
					service.validateEndTime(endTime);
					service.validateTestDuration(duration, startTime, endTime);
					OnlineTest onlineTest = new OnlineTest();
					onlineTest.setTestId(updateTestId);
					onlineTest.setTestName(test_name);
					onlineTest.setTestDuration(duration);
					onlineTest.setStartTime(startTime);
					onlineTest.setEndTime(endTime);
					onlineTest.setTestMarksScored(0.0);
					onlineTest.setTestTotalMarks(total_marks);
					OnlineTest updatedTest;
					try {
						updatedTest = service.updateTest(updateTestId, onlineTest);
						if (updatedTest != null) {
							System.out.println("OnlineTest Updated Successfully!");
						} else {
							System.out.println("OnlineTest cannot be Updated!");
						}
					} catch (UserException e) {
						// TODO Auto-generated catch block
						//System.out.println(e.getMessage());
						throw new UserException(e.getMessage());
					}
				} catch (UserException e) {
					//System.out.println(e.getMessage());
					throw new UserException(e.getMessage());
				} catch (Exception exception) {
					//System.out.println("Input entered in wrong format!");
					throw new UserException(exception.getMessage());
				}

			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException e) {
			//System.out.println("Invalid Input Type!");
			throw new UserException(e.getMessage());
		}
	}

	public static void deleteTest() throws UserException {
		System.out.println("Enter User Id");
		try {
			Long userId_DeleteTest = scanner.nextLong();
			try {
				service.validateUserId(userId_DeleteTest);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				//System.out.println(e.getMessage());
				throw new UserException(e.getMessage());
			}
			User deleteTestUser = service.searchUser(userId_DeleteTest);
			if (deleteTestUser.getIsAdmin()) {
				System.out.println("Enter the OnlineTest Id to be deleted");
				BigInteger deleteTestId = scanner.nextBigInteger();
				try {
					service.validateTestId(deleteTestId);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					//System.out.println(e.getMessage());
					throw new UserException(e.getMessage());
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
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Type!");
		}
	}

	public static void assignTestToUser() throws UserException {
		System.out.println("Enter User Id");
		try {
			Long user_Id = scanner.nextLong();
			try {
				service.validateUserId(user_Id);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				//System.out.println(e.getMessage());
				throw new UserException(e.getMessage());
			}
			User assignUser = service.searchUser(user_Id);
			if (assignUser.getIsAdmin()) {
				System.out.println("Enter the User Id to whom test is to be alloted");
				Long userTestId = scanner.nextLong();
				try {
					service.validateUserId(userTestId);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					//System.out.println(e.getMessage());
					throw new UserException(e.getMessage());
				}
				System.out.println("Enter the OnlineTest Id of the test to be alloted");
				BigInteger testUserId = scanner.nextBigInteger();
				try {
					service.validateTestId(testUserId);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					//System.out.println(e.getMessage());
					throw new UserException(e.getMessage());
				}
				Boolean assign = false;
				try {
					assign = service.assignTest(userTestId, testUserId);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					//System.out.println(e.getMessage());
					throw new UserException(e.getMessage());
				}
				if (assign) {
					System.out.println("OnlineTest assigned to User Successfully!");
				} else {
					System.out.println("OnlineTest could not be assigned to the User");
				}
			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Type!");
		}
	}

	public static void addQuestion() throws UserException {
		System.out.println("Enter User Id");
		try {
			Long userId_AddQuestion = scanner.nextLong();
			try {
				service.validateUserId(userId_AddQuestion);
			} catch (UserException e) {
				throw new UserException(e.getMessage());
			}
			User addQuestionUser = service.searchUser(userId_AddQuestion);
			if (addQuestionUser.getIsAdmin()) {
				System.out.println("Enter OnlineTest Id to which question is to be added");
				BigInteger questionTestId = scanner.nextBigInteger();
				try {
					service.validateTestId(questionTestId);
				} catch (UserException e) {
					throw new UserException(e.getMessage());
				}
				System.out.println("Enter the Question Id to be added");
				BigInteger questionId = scanner.nextBigInteger();
				try {
					service.validateQuestionId(questionId);
				} catch (UserException e) {
					throw new UserException(e.getMessage());
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
				} catch (UserException e) {
					throw new UserException(e.getMessage());
				}
				System.out.println("Enter the Question Marks");
				Double questionMarks = scanner.nextDouble();

				Question question = new Question();
				question.setQuestionId(questionId);
				question.setQusetionOptions(questionOptions);
				question.setQuestionTitle(questionTitle);
				question.setQuestionAnswer(questionAnswer - 1);
				question.setQuestionMarks(questionMarks);

				Question addQuestion;
				try {
					addQuestion = service.addQuestion(questionTestId, question);
					if (addQuestion != null) {
						System.out.println("Question added successfully!");
					} else {
						System.out.println("Question could not be added!");
					}
				} catch (UserException e) {
					throw new UserException(e.getMessage());
				}
			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Type!");
		}
	}

	public static void updateQuestion() throws UserException {
		System.out.println("Enter User Id");
		try {
			Long userId_UpdateQuestion = scanner.nextLong();
			try {
				service.validateUserId(userId_UpdateQuestion);
			} catch (UserException e) {
				throw new UserException(e.getMessage());
			}
			User updateQuestionUser = service.searchUser(userId_UpdateQuestion);
			if (updateQuestionUser.getIsAdmin()) {
				System.out.println("Enter the OnlineTest Id to be updated");
				BigInteger updateTestQuestionId = scanner.nextBigInteger();
				try {
					service.validateTestId(updateTestQuestionId);
				} catch (UserException e) {
					throw new UserException(e.getMessage());
				}
				System.out.println("Enter Question Id to be updated");
				BigInteger updateQuestionId = scanner.nextBigInteger();
				try {
					service.validateQuestionId(updateQuestionId);
				} catch (UserException e) {
					throw new UserException(e.getMessage());
				}
				System.out.println("Enter the updated Question Id");
				BigInteger updatedQuestionId = scanner.nextBigInteger();
				try {
					service.validateQuestionId(updatedQuestionId);
				} catch (UserException e) {
					throw new UserException(e.getMessage());
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
					throw new UserException(e.getMessage());
				}
				System.out.println("Enter the updated Question Marks");
				Double updatedQuestionMarks = scanner.nextDouble();
				Question question = new Question();
				question.setQuestionId(updatedQuestionId);
				question.setQusetionOptions(updatedQuestionOptions);
				question.setQuestionTitle(updatedQuestionTitle);
				question.setQuestionAnswer(updatedQuestionAnswer - 1);
				question.setQuestionMarks(updatedQuestionMarks);
				Question updatedQuestion;
				try {
					updatedQuestion = service.updateQuestion(updateTestQuestionId, updateQuestionId, question);
					if (updatedQuestion != null) {
						System.out.println("Question Updated Successfully!");
					} else {
						System.out.println("Question could not be Updated!");
					}
				} catch (UserException e) {
					throw new UserException(e.getMessage());
				}
			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Type!");
		}
	}

	public static void deleteQuestion() throws UserException {
		System.out.println("Enter User Id");
		try {
			Long userId_DeleteQuestion = scanner.nextLong();
			try {
				service.validateUserId(userId_DeleteQuestion);
			} catch (UserException e) {
				throw new UserException(e.getMessage());
			}
			User deleteQuestionUser = service.searchUser(userId_DeleteQuestion);
			if (deleteQuestionUser.getIsAdmin()) {
				System.out.println("Enter the OnlineTest Id from which Question is to be deleted");
				BigInteger deleteTestQuestionId = scanner.nextBigInteger();
				try {
					service.validateTestId(deleteTestQuestionId);
				} catch (UserException e) {
					throw new UserException(e.getMessage());
				}
				System.out.println("Enter Question Id to be deleted");
				BigInteger deleteQuestionId = scanner.nextBigInteger();
				try {
					service.validateQuestionId(deleteQuestionId);
				} catch (UserException e) {
					throw new UserException(e.getMessage());
				}
				Question deletedQuestion;
				try {
					deletedQuestion = service.deleteQuestion(deleteTestQuestionId, deleteQuestionId);
					if (deletedQuestion != null) {
						System.out.println("Question Deleted Successfully!");
					} else {
						System.out.println("Question could not be Deleted!");
					}
				} catch (UserException e) {
					throw new UserException(e.getMessage());
				}
			} else {
				System.out.println("Not allowed to perform this action");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Type!");
		}
	}

	public static void registerUser() throws UserException {
		System.out.println("Enter the User Id");
		try {
			Long user_id = scanner.nextLong();
			try {
				service.validateUserId(user_id);
			} catch (UserException e) {
				throw new UserException(e.getMessage());
			}
			System.out.println("Enter the Username");
			scanner.nextLine();
			String user_name = scanner.nextLine();
			try {
				service.validateUserName(user_name);
			} catch (UserException e) {
				throw new UserException(e.getMessage());
			}
			System.out.println("Enter the Password");
			String password = scanner.next();
			try {
				service.validatePassword(password);
			} catch (UserException e) {
				throw new UserException(e.getMessage());
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
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Type!");
		}
	}

	public static void giveTest() throws UserException {
		System.out.println("Enter User Id");
		try {
			Long userId_AnswerQuestion = scanner.nextLong();
			try {
				service.validateUserId(userId_AnswerQuestion);
			} catch (UserException e) {
				throw new UserException(e.getMessage());
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
					throw new UserException(e.getMessage());
				}
			} else {
				System.out.println("Cannot answer question!");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Type!");
		}

	}

	public static void checkResult() throws UserException {
		System.out.println("Enter OnlineTest Id for which the result is to be checked");
		try {
			BigInteger resultTestId = scanner.nextBigInteger();
			try {
				service.validateTestId(resultTestId);
			} catch (UserException e) {
				throw new UserException(e.getMessage());
			}
			OnlineTest resultTest = service.searchTest(resultTestId);
			Double marksScored = service.getResult(resultTest);
			System.out.println("The Marks Scored are " + marksScored);
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Type!");
		}
	}
}