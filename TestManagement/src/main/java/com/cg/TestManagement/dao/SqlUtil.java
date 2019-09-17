package com.cg.TestManagement.dao;

public class SqlUtil {

	public static final String SAVETESTSQL = "insert into test(test_name, test_duration, test_total_marks,test_marks_scored, test_start_date_time, test_end_date_time) values(?,?,?,?,?,?)";
	public static final String SEARCHTESTSQL = "Select * from test where test_id = ? and is_deleted = 0";
	public static final String REMOVETESTSQL = "update test set is_deleted = 1 where test_id = ? and is_deleted = 0";
	public static final String UPDATETESTSQL = "update test set test_name=? ,test_duration=? , test_total_marks = ? , test_marks_scored = ? , test_start_date_time = ? , test_end_date_time = ?, test_is_assigned = ? where test_id=? and is_deleted = 0";
	public static final String SAVEQUESTIONSQL = "insert into Question(question_title, question_option_a, question_option_b, question_option_c, question_option_d, question_chosen_answer, question_correct_answer, question_marks, question_marks_scored, test_id) values(?,?,?,?,?,?,?,?,?,?)";
	public static final String SEARCHQUESTIONSQL = "Select * from question where question_id = ? and is_deleted = 0";
	public static final String REMOVEQUESTIONSQL = "update question set is_deleted = 1 where question_id = ? and is_deleted = 0";
	public static final String UPDATEQUESTIONSQL = "update question set question_title=? , question_option_a=? , question_option_b = ? , question_option_c = ? , question_option_d = ? , question_chosen_answer = ? , question_correct_answer = ? , question_marks= ? , question_marks_scored = ? , test_id = ? where question_id=? and is_deleted = 0";
	public static final String SAVEUSERSQL = "insert into User(user_name, user_password, user_is_admin, test_id) values(?,?,?,?)";
	public static final String SEARCHUSERSQL = "Select * from user where user_id = ? and is_deleted = 0";
	public static final String REMOVEUSERSQL = "update user set is_deleted = 1 where user_id = ? and is_deleted = 0";
	public static final String UPDATEUSERSQL = "update user set user_name=? , user_password=?, test_id = ? where user_id=? and is_deleted = 0";
	public static final String QUESTIONSETSQL = "Select * from question where test_id =? and is_deleted = 0";
	
	private SqlUtil(){
		super();
	}
	
}
