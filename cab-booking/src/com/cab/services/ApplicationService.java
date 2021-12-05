package com.cab.services;

import com.cab.models.Application;
import com.cab.models.Driver;
import com.cab.models.Location;
import com.cab.models.User;
import javafx.util.Pair;

import java.util.List;

public class ApplicationService {
    Application application;
    public ApplicationService() {
        this.application = new Application();
    }

    public void processHistoricalData(String driverName, int driverRating, String customerName, int customerRating) {
        addDriverRating(driverName, customerName, driverRating);
        addCustomerRating(customerName, driverName, customerRating);
    }

    private void addDriverRating(String driverName, String customerName, int rating) {
        if (!application.getDrivers().containsKey(driverName)) {
            Driver driver = new Driver(driverName);
            application.getDrivers().put(driverName, driver);
        }
        application.getDrivers().get(driverName).addRating(customerName, rating);
        System.out.println("Driver Rating Added");
    }

    private void addCustomerRating(String customerName, String driverName, int rating) {
        if (!application.getCustomers().containsKey(customerName)) {
            User customer = new User(customerName);
            application.getCustomers().put(customerName, customer);
        }
        application.getCustomers().get(customerName).addRating(driverName, rating);
        System.out.println("Customer Rating Added");
    }



    public void getAvailableDriver(List<Pair<String, Location>> driversDetailsList, Pair<String, Location> customerDetails) {
        String customerName = customerDetails.getKey();
        if (!application.getCustomers().containsKey(customerName)) {
            User user = new User(customerName);
            application.getCustomers().put(customerName, user);
        }
        User customer = application.getCustomers().get(customerName);
        System.out.println("Average Rating : " + customer.getAverageRating());
        findEligibleDriveBasedOnRating(customer, driversDetailsList);

        findByDistance(customerDetails, driversDetailsList);

    }

    private void findByDistance(Pair<String, Location> customerDetails, List<Pair<String, Location>> driversDetailsList) {

    }

    private void findEligibleDriveBasedOnRating(User customer, List<Pair<String, Location>> driversDetailsList) {
        StringBuilder driverList = new StringBuilder("Eligible Driver :");
        double customerRating = customer.getAverageRating();
        for (Pair<String, Location> driverDetails : driversDetailsList) {
            Driver driver = application.getDrivers().get(driverDetails.getKey());
            if (driver != null) {
                double driverRating = driver.getAverageRating();
                if (driverRating >= customerRating) {
                    driverList.append("\n" + driverDetails.getKey() + ", " + driverRating);
                }
            }
        }
        System.out.println(new String(driverList));
    }

    public Pair<String, Location> praseInput(String line) {
        String[] command = line.split(",");
        if (command.length != 3) {
            System.out.println("Invalid Input");
            System.out.println("Enter like : d1,1,2");
            return null;
        }
        return new Pair<>(command[0], new Location(Integer.parseInt(command[1]), Integer.parseInt(command[2])));
    }
}
