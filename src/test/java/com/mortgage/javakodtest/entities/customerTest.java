package com.mortgage.javakodtest.entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class customerTest {

    customer customer;

    @BeforeEach
    //Using the first row from the prospects list for test
    //Sets the customer value to this before every tests
    public void reset() {
        customer = new customer("Juha", 1000, 5, 2);
    }

    @Test
    //Tests getName method from customer entity
    void testGetName() {
        assertEquals("Juha", customer.getName());
    }

    @Test
    //Tests setName method from customer entity
    void testSetName() {
        customer.setName("Mathias");
        assertEquals("Mathias", customer.getName());
    }

    @Test
    //Tests getTotalLoan method from customer entity
    void testGetTotalLoan() {
        assertEquals(1000, customer.getTotalLoan());
    }

    @Test
    //Tests setTotalLoan method from customer entity
    void testSetTotalLoan() {
        customer.setTotalLoan(125.55);
        assertEquals(125.55, customer.getTotalLoan());
        customer.setTotalLoan(597.32);
        assertEquals(597.32, customer.getTotalLoan());
        customer.setTotalLoan(16.16);
        assertEquals(16.16, customer.getTotalLoan());
    }

    @Test
    //Tests getInterest method from customer entity
    void testGetInterest() {
        assertEquals(5, customer.getInterest());
    }

    @Test
    //Tests setInterest method from customer entity
    void testSetInterest() {
        customer.setInterest(59);
        assertEquals(59, customer.getInterest());
        customer.setInterest(62);
        assertEquals(62, customer.getInterest());
        customer.setInterest(64);
        assertEquals(64, customer.getInterest());
    }

    @Test
    //Tests getYears method from customer entity
    void testGetYears() {
        assertEquals(2, customer.getYears());
    }

    @Test
    //Tests setYears method from customer entity
    void testSetYears() {
        customer.setYears(48);
        assertEquals(48, customer.getYears());
        customer.setYears(12);
        assertEquals(12, customer.getYears());
        customer.setYears(-5);
        assertEquals(-5, customer.getYears());
    }

    @Test
    //Tests getMonthlyPayment method from customer entity
    void testGetMonthlyPayment() {
        DecimalFormat df = new DecimalFormat("#.##");
        String expected = df.format(43.87);
        assertEquals(expected, customer.getMonthlyPayment());
    }
}