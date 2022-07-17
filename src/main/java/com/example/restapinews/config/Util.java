package com.example.restapinews.config;
import com.example.restapinews.Entity.Articles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class Util {
    public String getProperty(String propertyName) {
        String propertyValue = "";
        try (FileInputStream fileInputStream = new FileInputStream("E:\\PROJECTS\\restapinews\\src\\main\\resources\\get.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException ignored) {
        }
        return propertyValue;
    }
    public void order(List<Articles> articles) {
        articles.sort((Comparator) (o1, o2) -> {

            String publishedDate1 = ((Articles) o2).getPublished_date();
            String publishedDate2 = ((Articles) o1).getPublished_date();
            int sComp = publishedDate1.compareTo(publishedDate2);

            if (sComp != 0) {
                return sComp;
            }

            String newsSite1 = ((Articles) o1).getNews_site();
            String newsSite2 = ((Articles) o2).getNews_site();
            return newsSite1.compareTo(newsSite2);
        });
    }
}
