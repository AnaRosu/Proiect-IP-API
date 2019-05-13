package com.company;
import org.w3c.dom.Document;

import java.io.File;
import java.lang.Object;

public class Main {

    public static void main(String[] args) {
        Problem pr = new Problem();
        //pr.testIfFunctioning();
        //System.out.println(pr.getEnuntProblema(1));
        //Document doc    = pr.getTeste(1);
       XmlProblems problems = new XmlProblems();
       problems.getProbleme();

        File xmlFile = new File("D:\\problem.xml");
        pr.addProblem(xmlFile);
    }

}