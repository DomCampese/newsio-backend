package com.newsio.services;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaStackService {
    @Autowired
    HttpClient httpClient;

    public String sendGet(String searchText) throws Exception {
        String sortParam = "&sort=" + URLEncoder.encode("published_desc", StandardCharsets.UTF_8);
        String languageParam = "&languages=en";
        String url = "http://api.mediastack.com/v1/news?access_key=88c7ef9c74b205e86bc9af36f0ba94cd" + sortParam + languageParam + "&language=en&keywords=";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url.concat(URLEncoder.encode(searchText, StandardCharsets.UTF_8))))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}