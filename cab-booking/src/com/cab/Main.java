package com.cab;

import com.cab.constants.Constants;
import com.cab.models.Location;
import com.cab.services.ApplicationService;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        ApplicationService applicationService = new ApplicationService();
        Scanner input = new Scanner(new File(args[0]));

        System.out.println("Enter Historical Data and Enter Exit to complete historical data");

        while(input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase(Constants.EXIT)) {
                break;
            }
            String[] commands = line.split(" ");
            if (commands.length == 4) {
                applicationService.processHistoricalData(commands[0], Integer.parseInt(commands[1]), commands[2], Integer.parseInt(commands[3]));
            }
        }
        while(true) {
            System.out.println("Enter Continue or Exit like C or E");
            String userPref = input.nextLine();
            if (userPref.equalsIgnoreCase(Constants.E)) {
                break;
            }
            System.out.println("Enter Number of Available Driver");
            int numberOfDriver = Integer.parseInt(input.nextLine());
            List<Pair<String, Location>> driverList = new ArrayList<>();
            System.out.println("Enter Driver list like: d1,1,0");
            for (int i = 0; i < numberOfDriver; i++) {
                String line = input.nextLine();
                driverList.add(applicationService.praseInput(line));
            }
            System.out.println("Enter passenger details");
            Pair<String, Location> customerDetails = applicationService.praseInput(input.nextLine());

            applicationService.getAvailableDriver(driverList, customerDetails);
        }
    }
}
