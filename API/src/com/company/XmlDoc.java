package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.*;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlDoc {


    public static Document createXML(ResultSet rs, String pathName, String tagName)
            throws ParserConfigurationException, SQLException {
        //se creeaza un nou document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        //elementul ROOT
        //radacina documentului XML
        Element results = doc.createElement(tagName);
        doc.appendChild(results);
        //Obținem meta-datele
        //acestea sunt folosite pentru a structura documentul
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();

        while (rs.next()) {   //adaugam elementele de tip Row
            Element row = doc.createElement("Row");
            results.appendChild(row);

            for (int i = 1; i <= colCount; i++) {
                String columnName = rsmd.getColumnName(i); //numele coloanei
                Object value = rs.getObject(i); //vaoarea coloanei
                //un nou element este creat pentru fiecare coloana si adaugat la randul corespunzator
                Element node = doc.createElement(columnName);
                node.appendChild(doc.createTextNode(value.toString()));
                row.appendChild(node);
            }
        }
        // sciem continutul intr-un fisier XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pathName));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}
