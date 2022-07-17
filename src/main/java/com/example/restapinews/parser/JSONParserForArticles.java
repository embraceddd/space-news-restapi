package com.example.restapinews.parser;

import com.example.restapinews.Entity.Articles;
import com.example.restapinews.config.Util;
import com.example.restapinews.template.RestNewsTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONParserForArticles {

    static Util util = new Util();

    static public List<Articles> getArticle() {

        List<Articles> articlesList = new ArrayList<>();
        int limit = Integer.parseInt(util.getProperty("total_news_limit"));

        ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(util.getProperty("pool_size")));
        Runnable runnable = () -> {

            AtomicInteger newsPerThread = RestNewsTemplate.newsPerThread;

            JSONArray jsonArray;
            synchronized (articlesList) {
                jsonArray = new JSONArray(RestNewsTemplate.getResponse(String.valueOf(newsPerThread)));
                RestNewsTemplate.currentPosition.set(RestNewsTemplate.currentPosition.get() + newsPerThread.intValue());
                for (int i = 0; i < jsonArray.length(); i++) {
                    if (articlesList.size() >= limit) {
                        break;
                    }
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String newsSite = jsonObject.getString("newsSite");
                    String publishedAt = jsonObject.getString("publishedAt");
                    String summary = jsonObject.getString("summary");
                    articlesList.add(new Articles(title, newsSite, publishedAt, summary));
                }
            }
        };

        while (articlesList.size() < limit) {
            executor.execute(runnable);
        }

        executor.shutdown();

        util.order(articlesList);

        return articlesList;
    }

    static boolean parseNewsByTitle(String title) {
        Pattern pattern = Pattern.compile(util.getProperty("ban_words"), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(title);
        return matcher.find();
    }
}
