package com.skillsup.patterns.dto;


import com.skillsup.patterns.UserRole;
import com.skillsup.patterns.database.DataBase;

public class User {
	private final long id;
	private final String login;
	private final String password;
	private final UserRole[] userRole;

	public User(long id, String login, String password, UserRole[] userRole) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.userRole = userRole;
	}

	public long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public UserRole[] getUserRole() {
		return userRole;
	}

	public  static UserBuilder builder(){
		return new UserBuilder();
	}

	public static class UserBuilder{

		private  long id;
		private  String login;
		private  String password;
		private  UserRole[] userRole;

		public UserBuilder id(long id){
			this.id=id;
			return this;
		}
		public UserBuilder login(String login){
			this.login=login;
			return this;
		}
		public UserBuilder password(String password){
			this.password=password;
			return this;
		}
		public UserBuilder userRole(UserRole[] userRole){
			this.userRole=userRole;
			return this;
		}

		public User build(){
			return new User(this.id,this.login,this.password,this.userRole);
		}

	}



}
