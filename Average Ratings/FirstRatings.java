
/**
 * Write a description of FirstRatings here.
 * 
 * @Yiman Liu
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        FileResource fr = new FileResource(filename);
        ArrayList<Movie> list = new ArrayList<Movie>();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord rec : parser) {
            String id = rec.get("id");
            String title = rec.get("title");
            String year= rec.get("year");
            String genres = rec.get("genre");
            String director = rec.get("director");
            String country = rec.get("country");
            String poster = rec.get("poster");
            int minutes = Integer.parseInt(rec.get("minutes"));  
            Movie mov = new Movie(id, title, year, genres, director, country, poster, minutes);
            list.add(mov);
        }
        return list;
    }
    
    public int numOfMovies(String filename) {
        int num = 0;
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord rec : parser) {
            if (rec.get("genre").contains("Comedy")) {
                num++;
            }
        }
        return num;
    }
    
    public int MovieLength(String filename) {
        int num = 0;
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord rec : parser) {
            int min = Integer.parseInt(rec.get("minutes"));
            if (min > 150) {
                num++;
            }
        }
        return num;
    }    
    
    public int maxOfMovies(String filename) {
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (CSVRecord rec : parser) {
            if (!map.containsKey(rec.get("director"))) {
                map.put(rec.get("director"), 1);
            }
            else {
                int num = map.get(rec.get("director")) + 1;
                map.put(rec.get("director"), num);
            }
        }
        int max = 0;
        for (Integer v : map.values()) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }    
    
    void testLoadMovies() {
        ArrayList<Movie> list = loadMovies("data/ratedmovies_short.csv");
        for (Movie mov : list) {
            System.out.println(mov);       
        }
        //System.out.println(numOfMovies("data/ratedmoviesfull.csv"));
        //System.out.println(MovieLength("data/ratedmoviesfull.csv"));  
        //System.out.println(maxOfMovies("data/ratedmoviesfull.csv"));
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource(filename);
        CSVParser parser1 = fr.getCSVParser();        
        ArrayList<Rater> list = new ArrayList<Rater>();
        ArrayList<String> al = new ArrayList<String>();        
        for (CSVRecord rec : parser1) {
            String id = rec.get("rater_id");
            Rater rat = new Rater(id);            
            if (!al.contains(id)) {                   
                al.add(id);
                list.add(rat);                  
            }         
        }
        
        CSVParser parser2 = fr.getCSVParser();
        for (CSVRecord rec : parser2) {
            String id = rec.get("rater_id");            
            for (Rater rat : list) {
                if (rat.getID().equals(id)) {
                    rat.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));      
                }
            }            
        }
        return list;
    }
    
    public int numOfRatings(String filename, String rater) {
        FileResource fr = new FileResource(filename);
        int num = 0;
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord rec : parser) {
            if (rec.get("rater_id").equals(rater)) {
                num++;
            }
        }
        return num;      
    }

    public int maxOfRatings(String filename) {
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (CSVRecord rec : parser) {
            if (!map.containsKey(rec.get("rater_id"))) {
                map.put(rec.get("rater_id"), 1);
            }
            else {
                int num = map.get(rec.get("rater_id")) + 1;
                map.put(rec.get("rater_id"), num);
            }
        }
        int max = 0;
        for (Integer v : map.values()) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }     

    public int numOfRaters(String filename, String movie) {
        FileResource fr = new FileResource(filename);
        int num = 0;
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord rec : parser) {
            if (rec.get("movie_id").equals(movie)) {
                num++;
            }
        }
        return num;      
    }
    
    public int numOfDiffMovies(String filename) {
        FileResource fr = new FileResource(filename);
        ArrayList<String> list = new ArrayList<String>();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord rec : parser) {
            if (!list.contains(rec.get("movie_id"))) {
                list.add(rec.get("movie_id"));
            }
        }
        int num = list.size();
        return num;      
    }     
    
    void testLoasRaters() {
        ArrayList<Rater> list = loadRaters("data/ratings_short.csv");
        for (Rater rat : list) {
            System.out.println(rat);
        }
        System.out.println(list.size());
        //System.out.println(numOfRatings("data/ratings.csv", "193"));
        //System.out.println(maxOfRatings("data/ratings.csv")); 
        //System.out.println(numOfRaters("data/ratings.csv", "1798709"));
        //System.out.println(numOfDiffMovies("data/ratings.csv"));           
    }
    
}































