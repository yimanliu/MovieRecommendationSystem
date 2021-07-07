
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
    private double getAverageByID(String id, int minimalRaters) {
        int cnt = 0;
        double sum = 0.0;
        double average = 0.0;
        for (Rater rat : RaterDatabase.getRaters()) {
            if (rat.hasRating(id)) {
                System.out.println("check check " + rat.hasRating(id));
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
    
    private double dotProduct(Rater me, Rater r) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());       
        ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();
        for (String movieID : movies) {
            if(me.hasRating(movieID) && r.hasRating(movieID)) {
                list1.add(me.getRating(movieID) - 5.0);
                list2.add(r.getRating(movieID) - 5.0);
            }
        }
        Double[] l1 = new Double[list1.size()];
        Double[] l2 = new Double[list2.size()];
        l1 = list1.toArray(l1);
        l2 = list2.toArray(l2);
        double sum = 0.0;
        for (int i = 0; i < l1.length; i++) {
            sum += l1[i] * l2[i];
        }
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                double sum = dotProduct(me, r);
                if (sum > 0) {
                    Rating rating = new Rating(r.getID(), sum);
                    list.add(rating);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> topRatings = new ArrayList<Rating>();
        for (int i = 0; i < numSimilarRaters; i++) {
            topRatings.add(getSimilarities(id).get(i));
        }
        
        for (String mov : movies) {
            int cnt = 0;
            double sum = 0.0;
            double weighted = 0.0;
            double average = 0.0;            
            for (int j = 0; j < topRatings.size(); j++) {
                Rater r = RaterDatabase.getRater(topRatings.get(j).getItem());
                if (r.hasRating(mov)) {
                    cnt++;
                    weighted = r.getRating(mov) * topRatings.get(j).getValue();
                    sum += weighted;
                }
            }
            if (cnt >= minimalRaters) {
                average = sum / cnt;
                Rating rating = new Rating(mov, average);
                list.add(rating);
            }
        }
        Collections.sort(list, Collections.reverseOrder());        
        return list;        
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters,
    int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> topRatings = new ArrayList<Rating>();
        for (int i = 0; i < numSimilarRaters; i++) {
            topRatings.add(getSimilarities(id).get(i));
        }
        
        for (String mov : movies) {
            int cnt = 0;
            double sum = 0.0;
            double weighted = 0.0;
            double average = 0.0;            
            for (int j = 0; j < topRatings.size(); j++) {
                Rater r = RaterDatabase.getRater(topRatings.get(j).getItem());
                if (r.hasRating(mov)) {
                    cnt++;
                    weighted = r.getRating(mov) * topRatings.get(j).getValue();
                    sum += weighted;
                }
            }
            if (cnt >= minimalRaters) {
                average = sum / cnt;
                Rating rating = new Rating(mov, average);
                list.add(rating);
            }
        }
        Collections.sort(list, Collections.reverseOrder());        
        return list;        
    }       
    
}
