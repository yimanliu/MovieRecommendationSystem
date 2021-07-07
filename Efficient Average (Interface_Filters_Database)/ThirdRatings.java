
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings_short.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        int num = myRaters.size();
        return num;
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        int cnt = 0;
        double sum = 0.0;
        double average = 0.0;
        for (Rater rat : myRaters) {
            if (rat.hasRating(id)) {
                cnt++;
                double rating = rat.getRating(id);
                sum += rating;
            }
        }
        if (cnt < minimalRaters) {
            return 0.0;
        }
        else {
            average = sum / cnt;
        }
        return average;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movies) {
            double average = getAverageByID(id, minimalRaters);
            if (average != 0.0 ) {
                Rating rating = new Rating(id, average);
                list.add(rating);
            }
        }
        return list;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movies = new ArrayList<String>();
        movies = MovieDatabase.filterBy(filterCriteria);
        for (String id : movies) {
            double average = getAverageByID(id, minimalRaters);
            if (average != 0.0) {
                Rating rating = new Rating(id, average);
                list.add(rating);
            }
        }
        return list;
    }
    
}

























