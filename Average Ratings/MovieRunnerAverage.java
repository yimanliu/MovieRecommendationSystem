
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    void printAverageRatings() {
        SecondRatings sr = new SecondRatings();
        //System.out.println("movie size: " + sr.getMovieSize());
        //System.out.println("rater size: " + sr.getRaterSize());
        //System.out.println(sr.getAverageByID("0068646", 2));
        //System.out.println(sr.getAverageRatings(2));
        ArrayList<Rating> list = sr.getAverageRatings(3);
        Collections.sort(list);
        for (Rating r : list) {
            double average = r.getValue();
            String title = sr.getTitle(r.getItem());
            System.out.println(average + " " + title);
        }
    }
    
    void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings();
        ArrayList<Rating> list = sr.getAverageRatings(0);
        double average = 0.0;
        for (Rating r : list) {
            String title = "The Godfather";
            String id = sr.getID(title);
            if (r.getItem().equals(id)) {
                average = r.getValue();            
            }
        }        
        System.out.println(average);
    }
    
}






























