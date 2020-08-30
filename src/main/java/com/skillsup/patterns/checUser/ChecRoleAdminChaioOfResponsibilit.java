package com.skillsup.patterns.service;

import com.skillsup.patterns.UserRole;
import com.skillsup.patterns.database.DataBase;
import com.skillsup.patterns.dto.Credentials;
import com.skillsup.patterns.dto.User;

import java.util.Arrays;

public class ChecRoleAdminChaioOfResponsibilit extends ChainOfResponsibilityEntryPoint {

    private DataBase dataBase=DataBase.getInstanceInit(null);

    public boolean check(Credentials credentials) {
        User user=dataBase.findUser(credentials);
        if (Arrays.stream(user.getUserRole()).anyMatch(userRole-> userRole.equals(UserRole.COMMON))) {
            System.out.println("Your Admin!");
            return true;
        }

        return checkNext(credentials);
    }
}
