package com.newsio.models;
//class used to deserialize into from the api search results
public class NewsReponse {
    public String author;
    public String title;
    public String description;
    public String url;
    public String source;
    public String image; //the reponse for image is always null so catch it in a String
    public String category;
    public String language;
    public String country;
    public String published_at;
}
