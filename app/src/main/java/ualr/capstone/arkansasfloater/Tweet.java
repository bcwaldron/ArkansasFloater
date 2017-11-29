package ualr.capstone.arkansasfloater;

/**
 * Created by Bryan on 11/29/2017.
 */

public class Tweet {

    private String text;
    private String user;
    private double rating;

    public Tweet(String t, String u){

        text = t;
        user = u;
    }
    public Tweet(){}

    public String getText(){
        return text;
    }
    public String getUser(){
        return user;
    }
    public double getRating(){return rating;}
    public void setText(String t){
        text = t;
    }
    public void setUser(String u){
        user = u;
    }
    public void setRating(double r){rating = r;}
}
