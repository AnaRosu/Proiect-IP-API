package controller;

import org.junit.After;
import org.junit.Test;
import storage.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserTest {
    private User user = new User();

    @Test
    public void addUtilizator(){
        user.addUtilizator("maria","mypass","maria@yahoo.com");

        assertTrue("The user should exist in the database",user.utilizatorulExista("maria"));

    }

    @Test
    public void verificaUserParola(){
        assertEquals("Correct verification.",1,user.verificaUserParola("maria","mypass"));
        assertEquals("User does not exist.",-1,user.verificaUserParola("ion","mypass"));

    }

    @After
    public void returnToPreviousState(){
        Connection myConn = Database.getConnection();

        try{
            PreparedStatement st = myConn.prepareStatement("DELETE FROM utilizatori WHERE username = ? ;");
            st.setString(1, "maria");
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
}