package com.company;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilizator {

    Connection myConn = Database.getConnection();

    public int addUtilizator(String username, String password, String email) {
        //returneaza 1 daca username-ul nu exista in baza de date. Adauga utilizatorul
        //returneza -1 daca username-ul exista in baza de date. Nu adauga utilizatorul

        try {

            PreparedStatement statement = myConn.prepareStatement("select * from utilizatori where username=? ");
            statement.setString(1, username);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return -1; //inserare nereusita.
            }

            statement = myConn.prepareStatement("insert into utilizatori (username,password,email) values (?,?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; //inserare reusita
    }

    public int verificaUserParola(String username, String password) {
        //returneaza 1 daca username-ul si parola exista in baza de date
        //returneaza -1 daca cele doua nu exista.
        try {
            int availableId = 0;
            PreparedStatement statement = myConn.prepareStatement("select count(id) as valid from utilizatori WHERE username= ?  and password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                availableId = Integer.parseInt(rs.getString("valid"));
            }

            if (availableId == 1) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // nu avem in db userul si parola dorita

    }

}