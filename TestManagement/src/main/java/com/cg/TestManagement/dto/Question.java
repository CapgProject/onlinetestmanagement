package com.cg.TestManagement.dto;

import java.math.BigInteger;
import java.util.Arrays;

public class Question {

	private BigInteger questionId;
	private String[] questionOptions;
	private String questionTitle;
	private Integer questionAnswer;
	private Double questionMarks;
	private Integer chosenAnswer;
	private Double marksScored;
	private BigInteger testId;

	public Question() {
		super();
	}

	public Question(BigInteger questionId, String[] questionOptions, String questionTitle, Integer questionAnswer,
			Double questionMarks, Double marksScored, BigInteger testId) {
		super();
		this.questionId = questionId;
		this.questionOptions = questionOptions;
		this.questionTitle = questionTitle;
		this.questionAnswer = questionAnswer;
		this.questionMarks = questionMarks;
		this.chosenAnswer = -1;
		this.marksScored = marksScored;
		this.testId = testId;
	}

	public BigInteger getQuestionId() {
		return questionId;
	}

	public void setQuestionId(BigInteger questionId) {
		this.questionId = questionId;
	}

	public String[] getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(String[] questionOptions) {
		this.questionOptions = questionOptions;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public Integer getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(Integer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public Double getQuestionMarks() {
		return questionMarks;
	}

	public void setQuestionMarks(Double questionMarks) {
		this.questionMarks = questionMarks;
	}

	public Integer getChosenAnswer() {
		return chosenAnswer;
	}

	public void setChosenAnswer(Integer chosenAnswer) {
		this.chosenAnswer = chosenAnswer;
	}

	public Double getMarksScored() {
		return marksScored;
	}

	public void setMarksScored(Double marksScored) {
		this.marksScored = marksScored;
	}

	public BigInteger getTestId() {
		return testId;
	}

	public void setTestId(BigInteger testId) {
		this.testId = testId;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionOptions=" + Arrays.toString(questionOptions)
				+ ", questionTitle=" + questionTitle + ", questionAnswer=" + questionAnswer + ", questionMarks="
				+ questionMarks + ", chosenAnswer=" + chosenAnswer + ", marksScored=" + marksScored + ", testId="
				+ testId + "]";
	}

	@Override
	public int hashCode() {
		return this.questionId.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null) {
			return this.hashCode() == obj.hashCode();
		}
		else {
			return false;
		}
	}

}
