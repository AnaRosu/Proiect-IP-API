import org.junit.Test;

import java.sql.*;

public class ProblemTest {
    @Test
    public void getEnuntProblema() {
        Connection myConn = Database.getConnection();
        try {
            Statement st = myConn.createStatement();
            ResultSet myRs = st.executeQuery("select enunt from enunturi_probleme WHERE id = 2");
            while (myRs.next()) {
                System.out.println(myRs.getString("enunt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createXML() {

    }


    @Test
    public void getTeste() {
        Connection myConn = Database.getConnection();
        try {
            Statement st = myConn.createStatement();
            ResultSet result = st.executeQuery("select test1_in, test1_out from teste_probleme WHERE id = 1");
            while (result.next()) {
                System.out.println("Input " + result.getString("test1_in") + "  Output: " + result.getString("test1_out"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPunctaj() {
        Connection myConn = Database.getConnection();
        try {
            Statement st = myConn.createStatement();
           ResultSet result = st.executeQuery("select punctaj from punctaje WHERE id_utilizator = 1 and id_problema = 2");
            while (result.next()) {
                System.out.println(result.getString("punctaj"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}