package com.company;

import org.w3c.dom.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class XmlProblems {
    Connection myConn = Database.getConnection();
    XmlDoc documentProbleme = new XmlDoc();
    Document doc = null;
    public Document getProbleme(){
        try {
        PreparedStatement statement = myConn.prepareStatement("select proiectip12345.enunturi_probleme.titlu, " +
                "proiectip12345.enunturi_probleme.enunt from proiectip12345.enunturi_probleme");
        ResultSet rs = statement.executeQuery();
        doc = documentProbleme.createXML(rs, "D://probleme.xml", "Probleme");
        rs.close();
        statement.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

        return doc;
}

}
