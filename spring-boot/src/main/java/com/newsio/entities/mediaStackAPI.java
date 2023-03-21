package com.newsio.entities;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class mediaStackAPI {

    // one instance, reuse
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static void main(String[] args) throws Exception {

        mediaStackAPI obj = new mediaStackAPI();

        System.out.println("Testing 1 - Send Http GET request");
        obj.sendGet("cat");

    }

    public String sendGet(String searchText) throws Exception {
        String link = "http://api.mediastack.com/v1/news?access_key=88c7ef9c74b205e86bc9af36f0ba94cd&keywords=";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(link.concat(searchText)))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        //System.out.println(response.statusCode());

        // print response body
        //System.out.println(response.body());
        return response.body();
    }
}