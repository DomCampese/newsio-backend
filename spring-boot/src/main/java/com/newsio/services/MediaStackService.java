package com.newsio.services;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaStackService {
    @Autowired
    HttpClient httpClient;

    public String sendGet(String searchText, ArrayList<String> locationFilter, ArrayList<String> sourceFilter, ArrayList<String> categorieFilter, ArrayList<String> languageFilter) throws Exception {
        String url = "http://api.mediastack.com/v1/news?access_key=88c7ef9c74b205e86bc9af36f0ba94cd&keywords=";
        //NOTE: need to go through lists of optional filtes being passed in and concat them into the uri
        url = url.concat(searchText);

        //add in location filters
        if(locationFilter.size() > 0){
            url = url.concat("&countries=");
            for (int index = 0; index < locationFilter.size(); index++){
                url = url.concat(locationFilter.get(index).trim());
                //check to make sure its not the end of the items
                if(index != locationFilter.size()-1 ){
                    //each item needs to be seperated by ,
                    url = url.concat(",");
                }
            }
        }

        //add in source filters
        if(sourceFilter.size() > 0){
            url = url.concat("&sources=");
            for (int index = 0; index < sourceFilter.size(); index++){
                url = url.concat(sourceFilter.get(index).trim());
                //check to make sure its not the end of the items
                if(index != sourceFilter.size()-1 ){
                    //each item needs to be seperated by ,
                    url = url.concat(",");
                }
            }
        }

        //add in categorie filters
        if(categorieFilter.size() > 0){
            url = url.concat("&categories=");
            for (int index = 0; index < categorieFilter.size(); index++){
                url = url.concat(categorieFilter.get(index).trim());
                //check to make sure its not the end of the items
                if(index != categorieFilter.size()-1 ){
                    //each item needs to be seperated by ,
                    url = url.concat(",");
                }
            }
        }

        //add in language filters
        if(languageFilter.size() > 0){
            url = url.concat("&languages=");
            for (int index = 0; index < languageFilter.size(); index++){
                url = url.concat(languageFilter.get(index).trim());
                //check to make sure its not the end of the items
                if(index != languageFilter.size()-1 ){
                    //each item needs to be seperated by ,
                    url = url.concat(",");
                }
            }
        }

        //all optional filters should be applied at this point now ping the api
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    
}