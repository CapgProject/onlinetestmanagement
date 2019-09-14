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
		// TODO Auto-generated constructor stub
	}
	public OnlineTest(BigInteger testId, String testName, LocalTime testDuration, Set<Question> testQuestions,
			Double testTotalMarks, Double testMarksScored, LocalDateTime startTime, LocalDateTime endTime,
			Boolean isTestAssigned) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.testDuration = testDuration;
		this.testQuestions = testQuestions;
		this.testTotalMarks = testTotalMarks;
		this.testMarksScored = testMarksScored;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isTestAssigned = isTestAssigned;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((isTestAssigned == null) ? 0 : isTestAssigned.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((testDuration == null) ? 0 : testDuration.hashCode());
		result = prime * result + ((testId == null) ? 0 : testId.hashCode());
		result = prime * result + ((testMarksScored == null) ? 0 : testMarksScored.hashCode());
		result = prime * result + ((testName == null) ? 0 : testName.hashCode());
		result = prime * result + ((testQuestions == null) ? 0 : testQuestions.hashCode());
		result = prime * result + ((testTotalMarks == null) ? 0 : testTotalMarks.hashCode());
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
		OnlineTest other = (OnlineTest) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (isTestAssigned == null) {
			if (other.isTestAssigned != null)
				return false;
		} else if (!isTestAssigned.equals(other.isTestAssigned))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (testDuration == null) {
			if (other.testDuration != null)
				return false;
		} else if (!testDuration.equals(other.testDuration))
			return false;
		if (testId == null) {
			if (other.testId != null)
				return false;
		} else if (!testId.equals(other.testId))
			return false;
		if (testMarksScored == null) {
			if (other.testMarksScored != null)
				return false;
		} else if (!testMarksScored.equals(other.testMarksScored))
			return false;
		if (testName == null) {
			if (other.testName != null)
				return false;
		} else if (!testName.equals(other.testName))
			return false;
		if (testQuestions == null) {
			if (other.testQuestions != null)
				return false;
		} else if (!testQuestions.equals(other.testQuestions))
			return false;
		if (testTotalMarks == null) {
			if (other.testTotalMarks != null)
				return false;
		} else if (!testTotalMarks.equals(other.testTotalMarks))
			return false;
		return true;
	}
}
