create database OnlineTestManagement;
use OnlineTestManagement;

Test:

create table Test( test_id bigint PRIMARY KEY AUTO_INCREMENT, test_name varchar(50), test_duration time, test_total_marks double, test_marks_scored double, test_start_date_time datetime, test_end_date_time datetime, test_is_assigned bit default 0, is_deleted bit default 0);

User:
 
create table User(user_id bigint PRIMARY KEY AUTO_INCREMENT, user_name varchar(50), user_password varchar(20), user_is_admin bit default 0, test_id bigint,is_deleted bit default 0,FOREIGN KEY(test_id) REFERENCES Test(test_id));


Question:

create table Question(question_id bigint PRIMARY KEY AUTO_INCREMENT,question_title varchar(50), question_option_a varchar(50), question_option_b varchar(50),  question_option_c varchar(50),  question_option_d varchar(50), question_chosen_answer int, question_correct_answer int, question_marks double, question_marks_scored double,test_id bigint, is_deleted bit default 0, FOREIGN KEY(test_id) REFERENCES Test(test_id));

insert into Test(test_id, test_name, test_duration, test_total_marks, test_marks_scored, test_start_date_time, test_end_date_time) values(1,'Java', "0:30:00", 30, 30, "2019-09-14 19:30:10", "2019:10:16 19:30:10");

insert into Test(test_name, test_duration, test_total_marks, test_marks_scored, test_start_date_time, test_end_date_time) values('C', "0:30:00", 30, 30, "2019-09-13 19:30:10", "2019:10:20 19:30:10");


insert into Question(question_id,question_title, question_option_a, question_option_b, question_option_c, question_option_d, question_chosen_answer, question_correct_answer, question_marks, question_marks_scored, test_id) values(101,'Java is  _____ Language','Assembly','Procedural','Object Oriented','Declarative',3,3,30,30,1);

insert into Question(question_title, question_option_a, question_option_b, question_option_c, question_option_d, question_chosen_answer, question_correct_answer, question_marks, question_marks_scored, test_id) values('	C is ____ Language ','Assembly','Procedural','Object Oriented','Declarative',2,2,30,30,2);

insert into User(user_name, user_password, user_is_admin) values('Piyush','Piyush@123', 1);

insert into User(user_name, user_password, test_id) values('XYZ','Abc@345',2);