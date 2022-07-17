package com.example.restapinews;

import com.example.restapinews.Entity.Articles;
import com.example.restapinews.Repository.ArticlesRepository;
import com.example.restapinews.parser.JSONParserForArticles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppStartupDownloader implements ApplicationRunner {
    @Autowired
    ArticlesRepository articlesRepository;

    @Override
    public void run(ApplicationArguments args) {
        List<Articles> articlesList = JSONParserForArticles.getArticle();
        try {
            articlesRepository.saveAll(articlesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
