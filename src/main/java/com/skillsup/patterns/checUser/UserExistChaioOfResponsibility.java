package com.skillsup.patterns.service;

import com.skillsup.patterns.UserAuthenticator;
import com.skillsup.patterns.dto.Credentials;

public class UserExistChaioOfResponsibility extends ChainOfResponsibilityEntryPoint {

    private UserAuthenticator userAuthenticator;

    public UserExistChaioOfResponsibility(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }

    public boolean check(Credentials credentials) {
        if (!userAuthenticator.userIssue(credentials)) {
            System.out.println("This email is not registered!");
            return false;
        }

        return checkNext(credentials);
    }


}
