package com.contask.service;

import com.contask.model.ApiEndpoint;
import com.contask.model.Category;
import com.contask.model.Country;
import com.contask.model.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ArticleService {

    private RestTemplate restTemplate;
    private String apiKey = "7ad321a024144737968ec200d409dc99";

    @Autowired
    public ArticleService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ServiceResponse getResponse(ApiEndpoint apiEndpoint, Country country, Category category, int pageNumber, String phrase) {
        return restTemplate.getForObject(generateUrl(apiEndpoint, country, category, pageNumber, phrase), ServiceResponse.class);
    }

    public String generateUrl(ApiEndpoint apiEndpoint, Country country, Category category, int pageNumber, String phrase) {

        String url = "https://newsapi.org" + apiEndpoint.getUrlSubstring() + "?";
        for (int iCase = 0; iCase < 4; iCase++) {
            if (url.length() > 37 && !url.substring(url.length() - 1).equals("&")) {
                url += "&";
            }
            switch (iCase) {
                case 0:
                    if (country != null) {
                        url += "country=" + country.toString().toLowerCase();
                    }
                    break;
                case 1:
                    if (category != null) {
                        url += "category=" + category.toString().toLowerCase();
                    }
                    break;
                case 2:
                    if (phrase != null) {
                        url += "q=" + phrase;
                    }
                    break;
                case 3:
                    url += "page=" + pageNumber + "&apiKey=" + apiKey;
                    break;
            }
        }
        return url;
    }
}