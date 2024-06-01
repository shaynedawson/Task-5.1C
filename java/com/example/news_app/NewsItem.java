package com.example.news_app;

import java.io.Serializable;

public class NewsItem implements Serializable {
    private String title;
    private String description;
    private String imageUrl;

    public NewsItem(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
