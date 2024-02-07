package com.mortgage.javakodtest.repositories;
import com.mortgage.javakodtest.exceptions.fileReadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.mortgage.javakodtest.entities.customer;

public class customerRepositoryTests {
    customerRepository repository;
    ArrayList<customer> customerList;
    ArrayList<String> attributeList;

    @BeforeEach
    public void reset() throws fileReadException {
        repository = new customerRepository();
        customerList = repository.getCustomerList();
        attributeList = repository.getAttributeList();
    }

    @Test
    void testGetCustomerList() {
        customerList.add(new customer("Juha", 1000, 5, 2));
        repository.addCustomer(customerList.get(0));
        ArrayList<customer> testList = repository.getCustomerList();
        assertEquals(customerList, testList);
    }

    @Test
    void testGetAttributeList() {
        attributeList.add("Customer");
        attributeList.add("Total loan");
        attributeList.add("Interest");
        attributeList.add("Years");
        assertEquals(attributeList, repository.getAttributeList());
    }

    @Test
    void testAddCustomer() {
        customer customer = new customer("Juha", 1000, 5, 2);
        repository.addCustomer(customer);
        assertEquals(customer, repository.getCustomerList().get(repository.customerList.size()-1));
    }
}
