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

    public String sendGet(String searchText, ArrayList<String> locationFilter, ArrayList<String> sourceFilter, ArrayList<String> categorieFilter) throws Exception {
        String url = "http://api.mediastack.com/v1/news?access_key=88c7ef9c74b205e86bc9af36f0ba94cd&keywords=";
        //NOTE: need to go through lists of optional filtes being passed in and concat them into the uri
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url.concat(searchText)))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    
}