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
	
	public Question() {
		
	}
	public Question(BigInteger questionId, String[] questionOptions, String questionTitle, Integer questionAnswer,
			Double questionMarks, Integer chosenAnswer, Double marksScored) {
		super();
		this.questionId = questionId;
		this.questionOptions = questionOptions;
		this.questionTitle = questionTitle;
		this.questionAnswer = questionAnswer;
		this.questionMarks = questionMarks;
		this.chosenAnswer = chosenAnswer;
		this.marksScored = marksScored;
	}

	public BigInteger getQuestionId() {
		return questionId;
	}

	public void setQuestionId(BigInteger questionId) {
		this.questionId = questionId;
	}

	public String[] getQusetionOptions() {
		return questionOptions;
	}

	public void setQusetionOptions(String[] questionOptions) {
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

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionOptions=" + Arrays.toString(questionOptions)
				+ ", questionTitle=" + questionTitle + ", questionAnswer=" + questionAnswer + ", questionMarks="
				+ questionMarks + ", chosenAnswer=" + chosenAnswer + ", marksScored=" + marksScored + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chosenAnswer == null) ? 0 : chosenAnswer.hashCode());
		result = prime * result + ((marksScored == null) ? 0 : marksScored.hashCode());
		result = prime * result + ((questionAnswer == null) ? 0 : questionAnswer.hashCode());
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result + ((questionMarks == null) ? 0 : questionMarks.hashCode());
		result = prime * result + ((questionTitle == null) ? 0 : questionTitle.hashCode());
		result = prime * result + Arrays.hashCode(questionOptions);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (chosenAnswer == null) {
			if (other.chosenAnswer != null)
				return false;
		} else if (!chosenAnswer.equals(other.chosenAnswer))
			return false;
		if (marksScored == null) {
			if (other.marksScored != null)
				return false;
		} else if (!marksScored.equals(other.marksScored))
			return false;
		if (questionAnswer == null) {
			if (other.questionAnswer != null)
				return false;
		} else if (!questionAnswer.equals(other.questionAnswer))
			return false;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (questionMarks == null) {
			if (other.questionMarks != null)
				return false;
		} else if (!questionMarks.equals(other.questionMarks))
			return false;
		if (questionTitle == null) {
			if (other.questionTitle != null)
				return false;
		} else if (!questionTitle.equals(other.questionTitle))
			return false;
		if (!Arrays.equals(questionOptions, other.questionOptions))
			return false;
		return true;
	}
	
}
