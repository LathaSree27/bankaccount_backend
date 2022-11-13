package com.bankaccount.bankaccount.exception;

public class AlreadyExistingUser extends Exception {
    public AlreadyExistingUser() {
        super("An account already exists with this emailid.!");
    }
}
