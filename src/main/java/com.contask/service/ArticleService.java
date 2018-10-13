package com.contask.service;

import com.contask.model.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ArticleService {

    private RestTemplate restTemplate;

    @Autowired
    public ArticleService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ServiceResponse getResponse(String country, String category) {
        String url = "https://newsapi.org/v2/top-headlines?";
        if (country != null) {
            url = url + "country=" + country + "&";
        }
        if (category != null) {
            url = url + "category=" + category + "&";
        }
        url = url + "apiKey=7ad321a024144737968ec200d409dc99";
        return restTemplate.getForObject(url, ServiceResponse.class);
    }
}