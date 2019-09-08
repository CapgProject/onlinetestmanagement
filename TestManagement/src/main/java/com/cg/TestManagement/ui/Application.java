package com.cg.TestManagement.ui;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import com.cg.TestManagement.dao.Dao;
import com.cg.TestManagement.dao.DaoImpl;
import com.cg.TestManagement.dto.Question;
import com.cg.TestManagement.dto.Test;
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
			switch(choice) {
				case 1 : System.out.println("Enter the User Id");
				         long user_id = scanner.nextLong();
				         System.out.println("Enter the Username");
				         String user_name = scanner.next();
				         System.out.println("Enter the Password");
				         String password = scanner.next();
				         System.out.println("Are you an Admin: Enter 'y' for yes and 'n' for no");
				         String admin = scanner.next();
				         
				         User user = new User();
				         user.setUserId(user_id);
				         user.setUserName(user_name);
				         user.setUserPassword(password);
				         user.setUserTest(null);
				         if(admin.equalsIgnoreCase("y")) {
				        	 user.setIsAdmin(true);
				         }
				         else {
				        	 user.setIsAdmin(false);
				         }
				         
				         User register_user = service.registerUser(user);
				         if(register_user != null) {
				        	 System.out.println("User added successfully!!");
				         }
				         else {
				        	 System.out.println("User registration failed!!");
				         }
				         break;
				         
				case 2 : System.out.println("Enter User Id");
					 	 Long userId_AddTest = scanner.nextLong();
					 	 User foundUser = service.searchUser(userId_AddTest);
					 	 if(foundUser.getIsAdmin()) {
					 		 System.out.println("Enter Test Id");
					         BigInteger test_id = scanner.nextBigInteger();
					         System.out.println("Enter Test Name");
					         String test_name = scanner.next();
					         System.out.println("Enter Test Duration:Hours");
					         Integer durationHour = scanner.nextInt();
					         System.out.println("Enter Test Duration:Minutes");
					         Integer durationMinutes = scanner.nextInt();
					         System.out.println("Enter Test Duration:Seconds");
					         Integer durationSeconds = scanner.nextInt();
					         System.out.println("Enter Test Total Marks");
					         BigDecimal total_marks = scanner.nextBigDecimal();
					         System.out.println("Enter Start Time of Test:Year");
					         Integer startTimeYear = scanner.nextInt();
					         System.out.println("Enter Start Time of Test:Month");
					         Integer startTimeMonth = scanner.nextInt();
					         System.out.println("Enter Start Time of Test:Day");
					         Integer startTimeDay = scanner.nextInt();
					         System.out.println("Enter Start Time of Test:Hours");
					         Integer startTimeHour = scanner.nextInt();
					         System.out.println("Enter Start Time of Test:Minutes");
					         Integer startTimeMinutes = scanner.nextInt();
					         System.out.println("Enter Start Time of Test:Seconds");
					         Integer startTimeSeconds = scanner.nextInt();
					         System.out.println("Enter End Time of Test:Year");
					         Integer endTimeYear = scanner.nextInt();
					         System.out.println("Enter End Time of Test:Month");
					         Integer endTimeMonth = scanner.nextInt();
					         System.out.println("Enter End Time of Test:Day");
					         Integer endTimeDay = scanner.nextInt();
					         System.out.println("Enter End Time of Test:Hours");
					         Integer endTimeHours = scanner.nextInt();
					         System.out.println("Enter End Time of Test:Minutes");
					         Integer endTimeMinutes = scanner.nextInt();
					         System.out.println("Enter End Time of Test:Seconds");
					         Integer endTimeSeconds = scanner.nextInt();
					        
					         LocalTime duration = LocalTime.of(durationHour, durationMinutes, durationSeconds);
					         LocalDateTime startTime = LocalDateTime.of(startTimeYear, startTimeMonth, startTimeDay, startTimeHour, startTimeMinutes, startTimeSeconds);
					         LocalDateTime endTime = LocalDateTime.of(endTimeYear, endTimeMonth, endTimeDay, endTimeHours, endTimeMinutes, endTimeSeconds);
					         Test test = new Test();
					         test.setTestId(test_id);
					         test.setTestName(test_name);
					         test.setTestDuration(duration);
					         test.setStartTime(startTime);
					         test.setEndTime(endTime);
					         
					         Test addTest = service.addTest(test);
					         if(addTest != null) {
					        	 System.out.println("Test added successfully!");
					         }
					         else {
					        	 System.out.println("Test could not be added");
					         }
					 	 }
					 	 else {
							 System.out.println("Not allowed to perform this action");
						 }
					 	 break;
					 	 
				case 3 : System.out.println("Enter User Id");
						 Long userId_UpdateTest = scanner.nextLong();
						 User updateTestUser = searchUser(userId_UpdateTest);
						 if(updateTestUser.getIsAdmin()) {
							 System.out.println("Enter the Test Id to be updated");
							 BigInteger updateTestId = scanner.nextBigInteger();
							 Test updateTest = service.searchTest(updateTestId);
							 Test updatedTest = service.updateTest(updateTestId, updateTest);
							 if(updatedTest != null) {
								 System.out.println("Test Updated Successfully!");
							 }
							 else {
								 System.out.println("Test cannot be Updated!");
							 }
						 }
						 else {
							 System.out.println("Not allowed to perform this action");
						 }
						 break;
						 
				case 4 : System.out.println("Enter User Id");
						 Long userId_DeleteTest = scanner.nextLong();
						 User deleteTestUser = searchUser(userId_DeleteTest);
						 if(deleteTestUser.getIsAdmin()) {
							 System.out.println("Enter the Test Id to be deleted");
							 BigInteger deleteTestId = scanner.nextBigInteger();
							 Test deletedTest = service.deleteTest(deleteTestId);
							 if(deletedTest != null) {
								 System.out.println("Test Deleted Successfully!");
							 }
							 else {
								 System.out.println("Test could not be Deleted!");
							 }
						 }
						 else {
							 System.out.println("Not allowed to perform this action");
						 }
						 break;
					
				case 5 : System.out.println("Enter User Id");
				         Long user_Id = scanner.nextLong();
				         User assignUser = service.searchUser(user_Id);
				         if(assignUser.getIsAdmin()) {
				        	 System.out.println("Enter the User Id to whom test is to be alloted");
					         Long userTestId = scanner.nextLong();
					         System.out.println("Enter the Test Id of the test to be alloted");
					         BigInteger testUserId = scanner.nextBigInteger();
					         Boolean assign = service.assignTest(userTestId, testUserId);
					         if(assign) {
					        	 System.out.println("Test assigned to User Successfully!");
					         }
					         else {
					        	 System.out.println("Test could not be assigned to the User");
					         }
				         }
				         else {
				        	 System.out.println("Not allowed to perform this action");
				         }
				         break;
				         
				case 6 : System.out.println("Enter User Id");
				         Long userId_AddQuestion = scanner.nextLong();
				         User addQuestionUser = service.searchUser(userId_AddQuestion);
				         if(addQuestionUser.getIsAdmin()) {
				        	 System.out.println("Enter Test Id to which question is to be added");
				        	 BigInteger questionTestId = scanner.nextBigInteger();
				        	 System.out.println("Enter the Question Id to be added");
				        	 BigInteger questionId = scanner.nextBigInteger();
				        	 System.out.println("Enter Question Options");
				        	 String questionOptions[] = new String[4];
					         for(int i = 0; i<4; i++) {
					        	 questionOptions[i] = scanner.next();
					         }
					         System.out.println("Enter Question Title");
					         String questionTitle = scanner.nextLine();
					         System.out.println("Enter the Question Answer");
					         Integer questionAnswer = scanner.nextInt();
					         System.out.println("Enter the Question Marks");
					         BigDecimal questionMarks = scanner.nextBigDecimal();
					         
					         Question question = new Question();
					         question.setQuestionId(questionId);
					         question.setQusetionOptions(questionOptions);
					         question.setQuestionTitle(questionTitle);
					         question.setQuestionAnswer(questionAnswer);
					         question.setQuestionMarks(questionMarks);
					         
					         Question addQuestion = service.addQuestion(questionTestId, question);
					         if(addQuestion != null) {
					        	 System.out.println("Question added successfully!");
					         }
					         else {
					        	 System.out.println("Question could not be added!");
					         }
				         }
				         else {
				        	 System.out.println("Not allowed to perform this action");
				         }
						 break;
				
				case 7 : System.out.println("Enter User Id");
						 Long userId_UpdateQuestion = scanner.nextLong();
						 User updateQuestionUser = searchUser(userId_UpdateQuestion);
						 if(updateQuestionUser.getIsAdmin()) {
							 System.out.println("Enter the Test Id to be updated");
							 BigInteger updateTestQuestionId = scanner.nextBigInteger();
							 System.out.println("Enter Question Id to be updated");
							 BigInteger updateQuestionId = scanner.nextBigInteger();
							 Question question = new Question();
							 
							 Question updatedQuestion = service.updateQuestion(updateTestQuestionId, updateQuestionId, question);
							 if(updatedQuestion != null) {
								 System.out.println("Question Updated Successfully!");
							 }
							 else {
								 System.out.println("Question could not be Updated!");
							 }
						 }
						 else {
							 System.out.println("Not allowed to perform this action");
						 }
						 break;
				
				case 8 : System.out.println("Enter User Id");
						 Long userId_DeleteQuestion = scanner.nextLong();
						 User deleteQuestionUser = searchUser(userId_DeleteQuestion);
						 if(deleteQuestionUser.getIsAdmin()) {
							 System.out.println("Enter the Test Id from which Question is to be deleted");
							 BigInteger deleteTestQuestionId = scanner.nextBigInteger();
							 System.out.println("Enter Question Id to be deleted");
							 BigInteger deleteQuestionId = scanner.nextBigInteger();
							 
							 Question deletedQuestion = service.deleteQuestion(deleteTestQuestionId, deleteQuestionId); 
							 if(deletedQuestion != null) {
								 System.out.println("Question Deleted Successfully!");
							 }
							 else {
								 System.out.println("Question could not be Deleted!");
							 }
						 }
						 else {
							 System.out.println("Not allowed to perform this action");
						 }
						 break;
				
				case 9 : System.out.println("Enter Test Id for which the result is to be checked");
				         BigInteger resultTestId = scanner.nextBigInteger();
				         
				         Test resultTest = service.searchTest(resultTestId);
				         BigDecimal marksScored = service.getResult(resultTest);
				         System.out.println("The Marks Scored are "+ marksScored);				        	 
						 break;
				         
				case 10 : Map<Long, User> userDatabase = service.showUsers();
						  for (Map.Entry<Long, User> entry : userDatabase.entrySet()) {
						      System.out.println(entry.getKey()+" : "+entry.getValue().toString());
						  }
		                  break;
		                  
				case 11 : Map<BigInteger, Test> testDatabase = service.showTests();
						  for (Map.Entry<BigInteger, Test> entry : testDatabase.entrySet()) {
						      System.out.println(entry.getKey()+" : "+entry.getValue().toString());
						  }
		                  break;
					
				case 12 : Map<BigInteger, Question> questionDatabase = service.showQuestions();
						  for (Map.Entry<BigInteger, Question> entry : questionDatabase.entrySet()) {
						      System.out.println(entry.getKey()+" : "+entry.getValue().toString());
						  }
		                  break; 
		                  
				case 13 : System.exit(0);
						  break;
						  
				default : break;
			}
			
		}while(choice != 13);
		
	}

	public static void displayOptions() {
		System.out.println("The available actions are:");
		System.out.println("1 to Register as a User");
		System.out.println("2 to Add Test");
		System.out.println("3 to Update Test");
		System.out.println("4 to Delete Test");
		System.out.println("5 to Assign test to a User");
		System.out.println("6 to Add Questions");
		System.out.println("7 to Update Questions");
		System.out.println("8 to Delete Questions");
		System.out.println("9 to Check Result");
		System.out.println("10 to Display all Users");
		System.out.println("11 to Display all Tests");
		System.out.println("12 to Display all Questions");
		System.out.println("13 to Exit");
	}
}
