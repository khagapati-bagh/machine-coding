package com.movie;

import com.movie.constants.Constants;
import com.movie.services.ApplicationService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Application Started");
        ApplicationService applicationService = new ApplicationService();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of movie want to onboard");
        int numberOfMovie = Integer.parseInt(input.nextLine());
        for (int i = 0; i < numberOfMovie; i++) {
            String line = input.nextLine().trim();
            int addMovieLength = Constants.ADD_MOVIE.length();
            String movieDetails = line.substring(addMovieLength, line.length() - addMovieLength - 1);
            applicationService.addMovie(movieDetails);
        }
        System.out.println("Enter number of user");
        int numberOfUser = Integer.parseInt(input.nextLine());
        for (int i = 0; i < numberOfMovie; i++) {
            String line = input.nextLine().trim();
            int addUserLength = Constants.ADD_USER.length();
            String userDetails = line.substring(addUserLength, line.length() - addUserLength - 1);
            applicationService.addUser(userDetails);
        }
        System.out.println("Add/Update/Delete Review or EXIT");
        while(true) {
            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase(Constants.EXIT)) {
                break;
            }
            String edit = line.substring(0, line.indexOf("\""));
            switch (edit) {
                case Constants.ADD_REVIEW:

                    break;
                case Constants.UPDATE_REVIEW:
                    break;
                case Constants.DELETE_REVIEW:
                    break;
                case Constants.LIST_REVIEW:
                    break;
                case Constants.LIST_TOP:
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        System.out.println("Application Terminated");
    }
}
