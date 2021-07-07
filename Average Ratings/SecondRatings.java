
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmovies_short.csv", "data/ratings_short.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        int num = myMovies.size();
        return num;
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
        for (Movie mov : myMovies) {
            String id = mov.getID();
            double average = getAverageByID(id, minimalRaters);
            if (average != 0.0 ) {
                Rating rating = new Rating(id, average);
                list.add(rating);
            }
        }
        return list;
    }
    
    public String getTitle(String id) {
        String title = "the ID was not found";
        for (Movie mov : myMovies) {
            if (mov.getID().equals(id)) {
                title = mov.getTitle();            
            }
        }
        return title;
    }
    
    public String getID(String title) {
        String id = "NO SUCH TITLE";
        for (Movie mov : myMovies) {
            if (mov.getTitle().equals(title)) {
                id = mov.getID();
            }
        }
        return id;
    }
    
}





























