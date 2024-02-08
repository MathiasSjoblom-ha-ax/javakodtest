package com.mortgage.javakodtest.controllers;

import com.mortgage.javakodtest.entities.customer;
import com.mortgage.javakodtest.exceptions.addCustomerException;
import com.mortgage.javakodtest.repositories.customerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class customerControllerTests {
    // Creating a mock object of customerRepository for unit testing
    @Mock
    customerRepository customerRepository;
    // Creating a mock object of Model for unit testing
    @Mock
    Model model;
    // Creating a mock object of BindingResult for unit testing
    @Mock
    BindingResult bindingResult;
    // Creating an instance of customerController and injecting the mock objects into it for unit testing
    @InjectMocks
    customerController customerController;

    @BeforeEach
    public void reset() {
        MockitoAnnotations.openMocks(this);
    }

    //Tests the getCustomers method
    @Test
    void testGetCustomers() {
        //Creates a new list of customers
        List<customer> customers = new ArrayList<>();
        customers.add(new customer("Mathias", 300, 4, 10));
        customers.add(new customer("Matti", 500, 5, 15));
        //When getCustomerList method of customerRepository is called return the list of customers
        when(customerRepository.getCustomerList()).thenReturn((ArrayList<customer>) customers);

        //Calling the getCustomers method of customerController and storing the returned view name then verifying that view
        String view = customerController.getCustomers(model);
        assertEquals("viewCustomers", view);

        //Verifying that addAttribute method of model was called with "customers" as the attribute name and the list of customers as the value
        verify(customerRepository).getCustomerList();
        verify(model).addAttribute("customers", customers);
    }

    @Test
    void testAddCustomer() throws addCustomerException {
        //Creating a customer object
        customer customer = new customer("Mathias", 5000, 4.5, 6);
        //Specifying that when the hasErrors method of bindingResult is called, it should return false
        when(bindingResult.hasErrors()).thenReturn(false);

        //Calling the addCustomer method of customerController and storing the returned view name
        String view = customerController.addCustomer(customer, bindingResult);
        //Verifying that the addCustomer method of customerRepository was called with the customer object as the parameter
        verify(customerRepository).addCustomer(customer);
        //Verifying that the returned view name is viewCustomers
        assertEquals("viewCustomers", view);
    }

    @Test
    void testRedirectAddNew() {
        //Calling the redirectAdd method of customerController and storing the returned view name
        String view = customerController.redirectAdd();
        //Asserting that the returned view name is addCustomers
        assertEquals("addCustomers", view);
    }
}
