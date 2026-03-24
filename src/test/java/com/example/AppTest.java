package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void testRegexValid() {
        assertTrue("user123".matches("^\\w{3,20}$"));
    }

    @Test
    void testRegexInvalid() {
        assertFalse("ab".matches("^\\w{3,20}$"));
    }
}