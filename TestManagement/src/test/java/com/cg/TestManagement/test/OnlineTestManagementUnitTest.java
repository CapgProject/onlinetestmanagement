package com.cg.TestManagement.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.TestManagement.dto.OnlineTest;
import com.cg.TestManagement.dto.User;
import com.cg.TestManagement.exception.UserException;
import com.cg.TestManagement.service.Service;
import com.cg.TestManagement.service.ServiceImpl;

class OnlineTestManagementUnitTest {

	Service service;

	@BeforeEach
	void createObject() {
		service = new ServiceImpl();
	}

	@Test
	void assignTestUnitTest() throws UserException{
		OnlineTest test = new OnlineTest();
		test.setIsTestAssigned(false);
		User user = new User();

		Boolean assign = service.assignTest(user.getUserId(), test.getTestId());
		Assertions.assertEquals(new Boolean(true), assign);
		Assertions.assertEquals(new Boolean(true), test.getIsTestAssigned());
	}

	@Test
	void checkResultUnitTest() throws UserException {
		User user = new User();
		user.setIsAdmin(false);
		OnlineTest test = new OnlineTest();
		service.assignTest(user.getUserId(), test.getTestId());
		Double marksScored = test.getTestMarksScored();
		Double totalMarks = test.getTestTotalMarks();

		Assertions.assertTrue(totalMarks >= marksScored);

	}

	@AfterEach
	void deleteReference() {
		service = null;
	}

}