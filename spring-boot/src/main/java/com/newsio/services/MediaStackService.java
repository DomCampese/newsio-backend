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

    public String sendGet(
        String sources,
        String categories,
        String countries,
        String languages,
        String keywords,
        String date,
        String sort,
        String limit,
        String offset
    ) throws Exception {
        StringBuilder url = new StringBuilder("http://api.mediastack.com/v1/news?");
        AddQueryParam(url, "access_key", "029e185418a52da4afaa46a8b778f0c6"); //029e185418a52da4afaa46a8b778f0c6(new key) //88c7ef9c74b205e86bc9af36f0ba94cd (old key 484/500)
        AddQueryParam(url, "sources", sources);
        AddQueryParam(url, "categories", categories);
        AddQueryParam(url, "countries", countries);
        AddQueryParam(url, "languages", languages);
        AddQueryParam(url, "keywords", keywords);
        AddQueryParam(url, "date", date);
        AddQueryParam(url, "sort", sort);
        AddQueryParam(url, "limit", limit);
        AddQueryParam(url, "offset", offset);

        System.out.println("Fetching " + url.toString());

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(url.toString()))
            .setHeader("User-Agent", "Java 11 HttpClient Bot")
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private void AddQueryParam(StringBuilder url, String key, String value) {
        // Skip params with no value
        if (value == null || value.equals("")) {
            return;
        }
        String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8);
        url.append("&").append(key).append("=").append(encodedValue);
    }
    
}