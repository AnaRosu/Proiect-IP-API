package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProblemTest {

    @Test
    public void getEnuntProblema() {
        Problem pr = new Problem();
        assertEquals("Adunati 2 numere intregi x si y date la tastatura. ", pr.getEnuntProblema(5));
    }

    @Test
    public void getEnuntProblemaNull() {
        Problem pr = new Problem();
        assertEquals(null ,pr.getEnuntProblema(223));
    }

    @Test
    public void getPunctaj() {
        Problem pr = new Problem();
        assertEquals(3, pr.getPunctaj(1,5));
    }

    @Test
    public void getPunctajNull() {
        Problem pr = new Problem();
        assertEquals(-1,pr.getPunctaj(2,3));
    }
}