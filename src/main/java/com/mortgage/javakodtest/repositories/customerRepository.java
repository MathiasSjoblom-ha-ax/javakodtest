package com.mortgage.javakodtest.repositories;
import com.mortgage.javakodtest.entities.customer;
import com.mortgage.javakodtest.exceptions.fileReadException;
import com.mortgage.javakodtest.services.customerService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class customerRepository implements customerService{

    // List containing all the customers gathered from the text file
    ArrayList<customer> customerList = new ArrayList<>();
    // Name and location of the provided prospects list
    String fileName = "src/main/resources/prospects.txt";
    // Gathers the attributes provided from the prospects file (Customer,Total loan,Interest,Years)
    ArrayList<String> attributeList = new ArrayList<>();

    public customerRepository() throws fileReadException {
        readFile();
    }

    private void readFile() throws fileReadException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() < 8) {
                    continue;
                }
                //Removal of more unnecessary characters
                line = formatLine(line);
                String[] parts = line.split(",");
                //Put the attributes at first row into attributeList
                if (lineCount == 0) {
                    attributeList.addAll(Arrays.asList(parts));
                    lineCount++;
                    continue;
                }
                parts = formatParts(parts);
                try {
                    customerList.add(new customer(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Integer.parseInt(parts[3])));
                } catch (NumberFormatException e) {
                    throw new fileReadException("Error parsing data: " + e.getMessage());
                }
                lineCount++;
            }
        } catch (IOException e) {
            throw new fileReadException("Error reading file: " + e.getMessage());
        }
    }

    //Removes unnecessary comma character from the text file (not the nices but it does the trick for this case)
    private String formatLine(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                count++;
                if (count > 3) {
                    line = line.replaceFirst(",", " ");
                }
            }
        }
        return line.replaceAll("\"", "");
    }

    //Removes more unnecessary characters
    private String[] formatParts(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains(".")) {
                String[] newString = parts[i].split("\\.");
                parts[i] = parts[i].replaceAll("\\.", ",");
                double integer = Integer.parseInt(newString[0]);
                double decimal = Integer.parseInt(newString[1]);
                parts[i] = String.valueOf(integer + decimal / 100);
            }
        }
        return parts;
    }

    @Override
    public ArrayList<customer> getCustomerList() {
        return customerList;
    }

    @Override
    public ArrayList<String> getAttributeList() {
        return attributeList;
    }

    @Override
    public void addCustomer(customer customer) {
        this.customerList.add(customer);
    }
}
