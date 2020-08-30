package com.skillsup.patterns.impl;

import com.skillsup.patterns.UserAuthenticator;
import com.skillsup.patterns.UserRole;
import com.skillsup.patterns.database.DataBase;
import com.skillsup.patterns.dto.Credentials;
import com.skillsup.patterns.dto.User;
import com.skillsup.patterns.checUser.ChainOfResponsibilityEntryPoint;

import java.util.Map;

public class UserAuthenticatorImpl implements UserAuthenticator {

    private DataBase dataBase=DataBase.getInstanceInit(null);

    private ChainOfResponsibilityEntryPoint checkUser;


    @Override
    public UserRole authenticate(Credentials credentials) {
        return null;
    }

    @Override
    public boolean userIssue(Credentials credentials){

        if(dataBase.findUser(credentials)!=null){
            return true;
        }else{return false;}

    }

    @Override
    public void setMiddleware(ChainOfResponsibilityEntryPoint checkUser) {
        this.checkUser = checkUser;
    }

    @Override
    public boolean logIn(Credentials credentials, Map<UserRole, Boolean> access) {
        if (checkUser.check(credentials,access)) {
            System.out.println("Authorization have been successful!");
            return true;
        }
        return false;
    }


}
