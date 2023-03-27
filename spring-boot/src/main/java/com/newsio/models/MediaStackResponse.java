package com.newsio.models;

import java.util.ArrayList;

//class used to deserialize into from the api search results
public class MediaStackResponse {
    public PaginationInfo pagination;
    public ArrayList<NewsStoryInfo> data;
}
