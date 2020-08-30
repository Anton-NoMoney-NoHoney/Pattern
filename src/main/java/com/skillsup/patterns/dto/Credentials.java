package com.skillsup.patterns.dto;

public class Credentials {
	private final String login;
	private final String password;

	public Credentials(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public static CredentialsBuilder bilder(){
		return new CredentialsBuilder();
	}

	public static class CredentialsBuilder{
		private  String login;
		private  String password;

		public CredentialsBuilder login(String login){
			this.login=login;
			return this;
		}

		public CredentialsBuilder password(String password){
			this.password=password;
			return this;
		}

		public Credentials build(){
			return new Credentials(this.login,this.password);
		}
	}

}
