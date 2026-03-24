package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testValidUsername() {
        assertTrue("user123".matches("^\\w{3,20}$"));
    }

    @Test
    public void testInvalidUsername() {
        assertFalse("ab".matches("^\\w{3,20}$"));
    }
}