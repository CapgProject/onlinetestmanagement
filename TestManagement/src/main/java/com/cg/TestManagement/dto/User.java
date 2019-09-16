package com.cg.TestManagement.dto;

import java.math.BigInteger;

public class User {
	private BigInteger userId;
	private String userName;
	private String userPassword;
	private OnlineTest userTest;
	private Boolean isAdmin;

	public User() {
		super();
	}

	public User(BigInteger userId, String userName, String userPassword, OnlineTest userTest, Boolean isAdmin) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userTest = userTest;
		this.isAdmin = isAdmin;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public OnlineTest getUserTest() {
		return userTest;
	}

	public void setUserTest(OnlineTest userTest) {
		this.userTest = userTest;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userTest="
				+ userTest + ", isAdmin=" + isAdmin + "]";
	}

	@Override
	public int hashCode() {
		return this.userId.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null) {
			return this.hashCode() == obj.hashCode();
		}
		return false;
	}

}
