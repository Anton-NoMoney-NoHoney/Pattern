package com.skillsup.patterns.service;

import com.skillsup.patterns.database.DataBase;
import com.skillsup.patterns.dto.Credentials;

public abstract class ChainOfResponsibilityEntryPoint {


    private ChainOfResponsibilityEntryPoint next;

    public ChainOfResponsibilityEntryPoint linkWith(ChainOfResponsibilityEntryPoint next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(Credentials credentials);

    protected boolean checkNext(Credentials credentials) {
        if (next == null) {
            return true;
        }
        return next.check(credentials);
    }

}
