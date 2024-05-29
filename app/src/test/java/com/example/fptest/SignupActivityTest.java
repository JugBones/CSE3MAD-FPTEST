package com.example.fptest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SignupActivityTest {

    @Test
    public void testEmailValidation() {
        // Accepted email formats
        assertEquals(true, SignupActivity.isEmailValid("joshua@yahoo.com"));
        assertEquals(true, SignupActivity.isEmailValid("alex@gmail.com"));

        // Rejected email formats
        assertEquals(false, SignupActivity.isEmailValid(""));
        assertEquals(false, SignupActivity.isEmailValid("alex@gmailcom"));
        assertEquals(false, SignupActivity.isEmailValid("jairo@.com"));
        assertEquals(false, SignupActivity.isEmailValid("josh"));
    }

    @Test
    public void testValidatePassword() {
        // Accepted pass formats
        assertEquals(true, SignupActivity.validatePassword("Test1$"));

        // Rejected pass formats
        assertEquals(false, SignupActivity.validatePassword(""));
        assertEquals(false, SignupActivity.validatePassword("TEST1"));
        assertEquals(false, SignupActivity.validatePassword("test1"));
        assertEquals(false, SignupActivity.validatePassword("test1$"));
        assertEquals(false, SignupActivity.validatePassword("TEST1$"));
    }
}