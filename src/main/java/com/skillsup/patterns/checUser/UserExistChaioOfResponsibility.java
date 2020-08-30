package com.skillsup.patterns.checUser;

import com.skillsup.patterns.UserAuthenticator;
import com.skillsup.patterns.UserRole;
import com.skillsup.patterns.dto.Credentials;

import java.util.Map;

public class UserExistChaioOfResponsibility extends ChainOfResponsibilityEntryPoint {

    private UserAuthenticator userAuthenticator;

    public UserExistChaioOfResponsibility(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }

    public boolean check(Credentials credentials, Map<UserRole, Boolean> access) {
        if (!userAuthenticator.userIssue(credentials)) {
            System.out.println("This user is not registered!");
            return false;
        }

        return checkNext(credentials,access);
    }


}
