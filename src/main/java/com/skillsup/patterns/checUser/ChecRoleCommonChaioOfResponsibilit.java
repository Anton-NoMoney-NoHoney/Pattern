package com.skillsup.patterns.checUser;

import com.skillsup.patterns.UserRole;
import com.skillsup.patterns.database.DataBase;
import com.skillsup.patterns.dto.Credentials;
import com.skillsup.patterns.dto.User;

import java.util.Arrays;
import java.util.Map;

public class ChecRoleCommonChaioOfResponsibilit extends ChainOfResponsibilityEntryPoint  {

    private DataBase dataBase=DataBase.getInstanceInit(null);

    public boolean check(Credentials credentials, Map<UserRole, Boolean> access) {
        User user=dataBase.findUser(credentials);
        if (Arrays.stream(user.getUserRole()).anyMatch(userRole-> userRole.equals(UserRole.COMMON))) {
            System.out.println("Hello, User!");
            access.put(UserRole.COMMON,true);
            return checkNext(credentials,access);

        }
        return true;
    }
}
