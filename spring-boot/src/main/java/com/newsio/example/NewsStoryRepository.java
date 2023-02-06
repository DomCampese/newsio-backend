package com.newsio.example;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newsio.entities.NewsStory;

public interface NewsStoryRepository extends JpaRepository<NewsStory, Long> {

    NewsStory findByTitle(String title);

}

