package com.movie.services;

import com.movie.constants.Constants;
import com.movie.enums.CategoryType;
import com.movie.models.Application;
import com.movie.models.Movie;
import com.movie.models.Review;
import com.movie.models.User;

import java.util.Date;
import java.util.List;

public class ApplicationService {
    Application application;

    public ApplicationService() {
        this.application = new Application();
    }

    public void addUser(String userName) {
        if (application.getUsers().containsKey(userName)) {
            System.out.println("User with name : " + userName + " already exist");
            return;
        }
        User user = new User(userName);
        application.getUsers().put(userName, user);
        System.out.println("User Created with name : " + userName);
    }

    public void addMovie(String movieDetails) {
        String movieName = movieDetails.substring(1, movieDetails.indexOf("\"", 1));
        if (application.getMovies().containsKey(movieName)) {
            System.out.println("Movie with name : " + movieName + " already exist");
            return;
        }
        int releaseYear = Integer.parseInt(movieDetails.substring(movieDetails.indexOf(Constants.YEAR) + 6, 4));
        String genre = movieDetails.substring(movieDetails.indexOf(Constants.GENRE) + 7);
        int currentYear = new Date().getYear();
        Movie movie = new Movie(movieName, genre, releaseYear, releaseYear <= currentYear);
        application.getMovies().put(movieName, movie);
        System.out.println("Movie Created with name : " + movieName);
    }

    public void addReview(String userName, String movieName, int rate) {
        List<String> userReviewIds = application.getUserReview().get(userName);

        for (String userReviewId : userReviewIds) {
            if (application.getReviews().get(userReviewId).getMovieName().equalsIgnoreCase(movieName)) {
                System.out.println("Multiple Review Not Allowed");
                return;
            }
        }


        List<String> userReview = application.getUserReview().get(userName);
        if (userReview.size() + 1 > 2) {
            updateUserCategory(userName, userReview.size() + 1);
        }
        if (application.getUsers().get(userName).getCategoryType().equals(CategoryType.CRITIC)) {
            rate *= 2;
        } else if (application.getUsers().get(userName).getCategoryType().equals(CategoryType.EXPERT)) {
            rate *= 3;
        }
        Review review = new Review(rate, "", movieName);

        userReview.add(review.getReviewId());
        application.getUserReview().put(userName, userReview);

        System.out.println("Added Review for Movie : " + movieName + " By User : " + userName);
    }

    public void updateReview(String userName, String movieName, int rate) {
        List<String> userReviewIds = application.getUserReview().get(userName);
        for (String userReviewId : userReviewIds) {
            if (application.getReviews().get(userReviewId).getMovieName().equalsIgnoreCase(movieName)) {
                Review review = application.getReviews().get(userReviewId);
                review.setRate(rate);
                application.getReviews().put(review.getReviewId(), review);
                System.out.println("Review Updated for Move : " + movieName + " by User : " + userName);
                return;
            }
        }
        System.out.println("Review not found with the user and movie");
    }

    public void deleteReview(String userName, String movieName) {
        List<String> userReviewIds = application.getUserReview().get(userName);
        for (int i = 0; i < userReviewIds.size(); i++) {
            if (application.getReviews().get(userReviewIds.get(i)).getMovieName().equalsIgnoreCase(movieName)) {
                userReviewIds.remove(i);
                application.getUserReview().put(userName, userReviewIds);
                updateUserCategory(userName, userReviewIds.size());

                System.out.println("Review Deleted for Movie : " + movieName + " By User : " + userName);
                return;
            }
        }
        System.out.println("Not found any review with Movie : " + movieName + " reviewed by User : " + userName);
    }

    public void listReview(String userName) {
        List<String> userReviewIds = application.getUserReview().get(userName);
        StringBuilder displayReview = new StringBuilder("{");
        for (int i = 0; i < userReviewIds.size(); i++) {
            Review review = application.getReviews().get(userReviewIds.get(i));
            displayReview.append("\n\"");
            displayReview.append(review.getMovieName());
            displayReview.append("\":");
            displayReview.append(review.getRate());
            if (i < userReviewIds.size() - 1) {
                displayReview.append(",");
            }
        }
        displayReview.append("\n}");
        System.out.println(new String(displayReview));
    }

    public void listTopMovie(String movieDetails) {

    }

    public void listTopMovieByYear(int topNMovie, int year) {

    }

    public void listTopMovieByYearAndGenre(int topNMovie, int year, String genre) {

    }

    public void listTopMovieByYearAndUserCategory(int topNMovie, int year, CategoryType categoryType) {

    }

    private void updateUserCategory(String userName, int numberOfReview) {
        User user = application.getUsers().get(userName);
        if (numberOfReview < 3) {
            user.setCategoryType(CategoryType.VIEWER);
        } else if (numberOfReview < 5) {
            user.setCategoryType(CategoryType.CRITIC);
        } else {
            user.setCategoryType(CategoryType.EXPERT);
        }
    }
}
