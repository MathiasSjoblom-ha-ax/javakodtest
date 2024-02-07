package com.mortgage.javakodtest.services;
import com.mortgage.javakodtest.entities.customer;

import java.util.ArrayList;

public interface customerService {
    ArrayList<customer> getCustomerList();
    ArrayList<String> getAttributeList();
    void addCustomer(customer customer);
}
