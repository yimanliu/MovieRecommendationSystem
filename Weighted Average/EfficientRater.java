
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String, Rating> myMap;

    public EfficientRater(String id) {
        myID = id;
        myMap = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myMap.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (myMap.containsKey(item)) {
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (myMap.containsKey(item)) {
            double rating = myMap.get(item).getValue(); 
            return rating;
        }
        return -1;
    }

    public int numRatings() {
        return myMap.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String s : myMap.keySet()) {
            list.add(s);
        }
        return list;
    }
    
    public String toString () {
        String result = "Rater id=" + myID + " the number of ratings: " + numRatings() + "\n" + myMap;
        return result;
    }    
    
}
