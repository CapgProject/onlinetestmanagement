package com.cg.TestManagement.Exception;

public class ExceptionMessage {

	public static final String IDMESSAGE = "Id cannot be negative or null";
	public static final String NAMEMESSAGE = "Only Alphabets are allowed and the first character should be capitalized";
	public static final String VALIDATEMESSAGE = "UserPassword should contain at least one upper case character, one lower case character, one numeric character, one special character and length should be at least eight characters";
	public static final String TIMEMESSAGE = "End date cannot be before start date";
	public static final String ENDTIMEMESSAGE = "End date cannot be in the past";
	public static final String DURATIONMESSAGE = "The test duration cannot be more than the time between the start and end time";
	public static final String QUESTIONMESSAGE = "The question does not exist in the OnlineTest";
	public static final String USERMESSAGE = "The user does not exist";
	public static final String TESTMESSAGE = "The test does not exist or is already assigned";
	public static final String INVALIDQUESTIONANSWER = "The Question Answer can only be in the range of 0 to 3";
	public static final String TESTASSIGNEDMESSAGE = "The test is already assigned";
	public static final String INVALIDDATEMESSAGE = "Input entered in wrong format";
	public static final String INVALIDINPUTMESSAGE = "Invalid Input Type";
	public static final String DATABASEMESSAGE = "Database is full! Contact your database manager for further queries!";
}
