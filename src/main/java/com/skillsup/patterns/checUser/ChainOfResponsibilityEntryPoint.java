package com.skillsup.patterns.checUser;

import com.skillsup.patterns.UserRole;
import com.skillsup.patterns.dto.Credentials;

import java.util.Map;

public abstract class ChainOfResponsibilityEntryPoint {


    private ChainOfResponsibilityEntryPoint next;
    private Map<UserRole, Boolean> access;

    public ChainOfResponsibilityEntryPoint linkWith(ChainOfResponsibilityEntryPoint next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(Credentials credentials,Map<UserRole, Boolean> access);

    protected boolean checkNext(Credentials credentials,Map<UserRole, Boolean> access) {
        if (next == null) {
            return true;
        }
        return next.check(credentials,access);
    }

}
