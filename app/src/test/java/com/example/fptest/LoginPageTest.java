package com.example.fptest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LoginPageTest {

    @Test
    public void testIsEmailValid() {
        // Accepted email formats
        assertEquals(true, LoginActivity.isEmail("joshua@yahoo.com"));
        assertEquals(true, LoginActivity.isEmail("alex@gmail.com"));

        // Rejected email formats
        assertEquals(false, LoginActivity.isEmail(""));
        assertEquals(false, LoginActivity.isEmail("alex@gmailcom"));
        assertEquals(false, LoginActivity.isEmail("jairo@.com"));
        assertEquals(false, LoginActivity.isEmail("josh"));
    }

}
