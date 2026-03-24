package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testValidUsername() {
        assertTrue(App.isValidUsername("user123"));
    }

    @Test
    public void testInvalidUsernameShort() {
        assertFalse(App.isValidUsername("ab"));
    }

    @Test
    public void testInvalidUsernameSpecialChar() {
        assertFalse(App.isValidUsername("user@123"));
    }

    @Test
    public void testNullUsername() {
        assertFalse(App.isValidUsername(null));
    }
}