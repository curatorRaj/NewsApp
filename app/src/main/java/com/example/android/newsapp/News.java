package com.example.android.newsapp;

public class News {
    private  String image;
    private String title;
    private String news;
    private  String newsUrl;
    public News(String title,String image,String news, String newsUrl)
    {
        this.image = image;
        this.title = title;
        this.news = news;
        this.newsUrl = newsUrl;

    }

    public String getImage(){
        return image;

    }
    public String getTitle()
    {
        return title;

    }
    public String getNews()
    {
        return news;
    }

    public String getNewsUrl()
    {
        return newsUrl;
    }
}
