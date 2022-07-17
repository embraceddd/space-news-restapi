package com.example.restapinews.Entity;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
public class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String newsSite;
    private String publishedDate;

    @Column(length = 10_000)
    private String article;

    public Articles() {

    }

    public Articles(String title, String newsSite, String publishedAt, String summary) {
        this.title = title;
        this.newsSite = newsSite;
        this.publishedDate = publishedAt;
        this.article = summary;
    } // конструктор

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews_site() {
        return newsSite;
    }

    public void setNews_site(String news_site) {
        this.newsSite = news_site;
    }

    public String getPublished_date() {
        return publishedDate;
    }

    public void setPublished_date(String published_date) {
        this.publishedDate = published_date;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
