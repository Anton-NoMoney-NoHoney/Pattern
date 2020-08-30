package com.skillsup.patterns;

import com.skillsup.patterns.checUser.ChainOfResponsibilityEntryPoint;
import com.skillsup.patterns.dto.Credentials;
import com.skillsup.patterns.dto.User;

import java.util.Map;

public interface UserAuthenticator {

	/**
	 * This method is needed to identify who is trying to access the system
	 * @param credentials user passwrod and user unique login
	 * @return role of the user who's credentials are checked
	 */
	UserRole authenticate(Credentials credentials);

	boolean userIssue(Credentials credentials);

	void setMiddleware(ChainOfResponsibilityEntryPoint chainOfResponsibilityEntryPoint);

	boolean logIn(Credentials credentials, Map<UserRole, Boolean> access);


}
