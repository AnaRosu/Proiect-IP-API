package com.company;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void getConnection() {
        assertNotEquals(null,Database.getConnection());
    }

}