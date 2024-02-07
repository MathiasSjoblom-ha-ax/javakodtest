package com.mortgage.javakodtest.entities;

import java.text.DecimalFormat;

public class customer {

    String name;
    double totalLoan;
    double interest;
    int years;

    //Constructor for creation of customer
    public customer(String name, double totalLoan, double interest, int years) {
        this.name = name;
        this.totalLoan = totalLoan;
        this.interest = interest;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(double totalLoan) {
        this.totalLoan = totalLoan;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getMonthlyPayment() {

        //Mortgage calculator without math.h
        //Decimal format for correct output syntax ex (#.## = 4.10)
        DecimalFormat df = new DecimalFormat("#.##");
        double principal = totalLoan;
        double annualInterest = interest;
        double monthlyInterest = (annualInterest / 12) / 100;
        int numberOfPayments = years * 12;

        double result = 1.00;
        //Manual pow loop
        //This loop calculates (1 + monthlyInterest) raised to the power of numberOfPayments
        //It multiplies 'result' by (1 + monthlyInterest) for 'numberOfPayments' times
        for (int i = 0; i < numberOfPayments; i++) {
            result = result * (1 + monthlyInterest);
        }
        double monthlyPayment = (principal * result * monthlyInterest) / (result-1);
        return df.format(monthlyPayment);
    }
}