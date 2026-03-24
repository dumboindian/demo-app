package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AppTest {

    private DatabaseService dbMock;
    private App app;

    @Before
    public void setUp() {
        dbMock = Mockito.mock(DatabaseService.class);
        app = new App(dbMock);
    }

    @Test
    public void testUserExistsReturnsTrue() {
        Mockito.when(dbMock.userExists("john")).thenReturn(true);
        assertTrue(app.userExists("john"));
    }

    @Test
    public void testUserExistsReturnsFalse() {
        Mockito.when(dbMock.userExists("doe")).thenReturn(false);
        assertFalse(app.userExists("doe"));
    }
}