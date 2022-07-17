package com.example.restapinews.Repository;

import com.example.restapinews.Entity.Articles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends CrudRepository<Articles, Long> {

    List<Articles> findBynewsSite(String newsSite);

    @Override
    List<Articles> findAll();
}