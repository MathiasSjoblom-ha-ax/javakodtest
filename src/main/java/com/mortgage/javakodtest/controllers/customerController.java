package com.mortgage.javakodtest.controllers;
import com.mortgage.javakodtest.entities.customer;
import com.mortgage.javakodtest.exceptions.fileReadException;
import com.mortgage.javakodtest.repositories.customerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mortgage.javakodtest.exceptions.addCustomerException;

@Controller
public class customerController {

    customerRepository customerRepository;
    {
        try {
            customerRepository = new customerRepository();
        } catch (fileReadException e) {
            e.printStackTrace();
        }
    }

    //Mapping the root URL, adding list of customer from the repository to the model and returns the view to be rendered
    @RequestMapping("/")
    public String getCustomers(Model model) {
        model.addAttribute("customers", customerRepository.getCustomerList());
        return "viewCustomers";
    }

    //Mapping the /addCustomer URL to this method for POST requests
    //Adds the customer to the repository and throws addCustomerException for valid errors
    //Return the viewCustomers to be rendered
    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") customer customer, BindingResult result) throws addCustomerException {
        if(!result.hasErrors()) {
            try {
                customerRepository.addCustomer(customer);
                System.out.println("Successfully inserted new customer");
            } catch (Exception e) {
                throw new addCustomerException("Error inserting new customer");
            }
        } else {
            throw new addCustomerException("Validation failed for customer");
        }
        return "viewCustomers";
    }

    //Mapping the /addCustomer URL for get requests
    //returns the addCustomers to be rendered
    @GetMapping("/addCustomer")
    public String redirectAdd() {
        return "addCustomers";
    }
}
