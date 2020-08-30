package com.skillsup.patterns.checUser;

import com.skillsup.patterns.UserRole;
import com.skillsup.patterns.database.DataBase;
import com.skillsup.patterns.dto.Credentials;
import com.skillsup.patterns.dto.User;

import java.util.Arrays;
import java.util.Map;

public class ChecRoleBannedChaioOfResponsibilit extends ChainOfResponsibilityEntryPoint {

    private DataBase dataBase=DataBase.getInstanceInit(null);

    public boolean check(Credentials credentials, Map<UserRole, Boolean> access) {
        User user=dataBase.findUser(credentials);
        if (Arrays.stream(user.getUserRole()).anyMatch(userRole-> userRole.equals(UserRole.BANNED))) {
            System.out.println("Good-by, your banned!");
            access.put(UserRole.BANNED,true);
            return false;
        }
        return checkNext(credentials,access);
    }
}
