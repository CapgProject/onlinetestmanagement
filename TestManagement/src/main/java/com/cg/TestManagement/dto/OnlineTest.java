package com.cg.TestManagement.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class OnlineTest {
	private BigInteger testId;
	private String testName;
	private LocalTime testDuration;
	private Set<Question> testQuestions;
	private Double testTotalMarks;
	private Double testMarksScored;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Boolean isTestAssigned;

	public OnlineTest() {
		super();
	}

	public OnlineTest(String testName, LocalTime testDuration, Set<Question> testQuestions,
			Double testTotalMarks, Double testMarksScored, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.testId = null;
		this.testName = testName;
		this.testDuration = testDuration;
		this.testQuestions = testQuestions;
		this.testTotalMarks = testTotalMarks;
		this.testMarksScored = testMarksScored;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isTestAssigned = false;
	}

	public BigInteger getTestId() {
		return testId;
	}

	public void setTestId(BigInteger testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public LocalTime getTestDuration() {
		return testDuration;
	}

	public void setTestDuration(LocalTime testDuration) {
		this.testDuration = testDuration;
	}

	public Set<Question> getTestQuestions() {
		return testQuestions;
	}

	public void setTestQuestions(Set<Question> testQuestions) {
		this.testQuestions = testQuestions;
	}

	public Double getTestTotalMarks() {
		return testTotalMarks;
	}

	public void setTestTotalMarks(Double testTotalMarks) {
		this.testTotalMarks = testTotalMarks;
	}

	public Double getTestMarksScored() {
		return testMarksScored;
	}

	public void setTestMarksScored(Double testMarksScored) {
		this.testMarksScored = testMarksScored;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsTestAssigned() {
		return isTestAssigned;
	}

	public void setIsTestAssigned(Boolean isTestAssigned) {
		this.isTestAssigned = isTestAssigned;
	}

	@Override
	public String toString() {
		return "OnlineTest [testId=" + testId + ", testName=" + testName + ", testDuration=" + testDuration
				+ ", testQuestions=" + testQuestions + ", testTotalMarks=" + testTotalMarks + ", testMarksScored="
				+ testMarksScored + ", startTime=" + startTime + ", endTime=" + endTime + ", isTestAssigned="
				+ isTestAssigned + "]";
	}

	@Override
	public int hashCode() {
		return this.testId.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null)
		{
			return this.hashCode() == obj.hashCode();
		}
		else {
			return false;
		}
	}
}
