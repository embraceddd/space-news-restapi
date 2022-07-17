package com.example.restapinews.service;

import com.example.restapinews.Entity.Articles;
import com.example.restapinews.Repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private ArticlesRepository articlesRepository;

    public Articles getNewsById(Long id) {
        return articlesRepository.findById(id).get();
    }

    public List<Articles> getAllNews() {
        return articlesRepository.findAll();
    }

    public List<Articles> getNewsByNewssite(String newsSite) {
        return articlesRepository.findBynewsSite(newsSite);
    }
}
