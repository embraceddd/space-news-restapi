package com.example.restapinews.template;

import com.example.restapinews.config.Util;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

public class RestNewsTemplate {
    static RestTemplate restTemplate = new RestTemplate();
    static int start = Integer.parseInt(new Util().getProperty("start"));
    public static AtomicInteger currentPosition = new AtomicInteger(start);
    public static AtomicInteger newsPerThread = new AtomicInteger
            (Integer.parseInt
                    (new Util().getProperty
                            ("download_per_thread_limit")));

    public static String getResponse(String newsToDownload) {
        String url = "https://api.spaceflightnewsapi.net/v3/articles?_limit=" + newsToDownload + "&_start=" + currentPosition;
        return restTemplate.getForObject(url, String.class);
    }
}
