package com.example.restapinews.controller;


import com.example.restapinews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity getNewsById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(newsService.getNewsById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/allNews")
    public ResponseEntity getAllNews() {
        try {
            return ResponseEntity.ok(newsService.getAllNews());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("ошибка");
        }
    }

    @GetMapping("/{newsSite}")
    public ResponseEntity getNewsByNewsSite(@PathVariable String newsSite) {
        try {
            return ResponseEntity.ok(newsService.getNewsByNewssite(newsSite));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("нет такого сайта");
        }
    }
}
