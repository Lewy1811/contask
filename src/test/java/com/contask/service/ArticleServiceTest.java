package com.contask.service;

import com.contask.model.ApiEndpoint;
import com.contask.model.Category;
import com.contask.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleServiceTest {

    private ArticleService articleService;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    public void shouldGenerateTopHeadlinesUrlWithoutPhrase() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.TOP_HEADLINES, Country.pl, Category.bussiness, 1, null).
                equals("https://newsapi.org/v2/top-headlines?country=pl&category=bussiness&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateTopHeadlinesUrlWithoutCategory() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.TOP_HEADLINES, Country.pl, null, 1, "fingerstyle").
                equals("https://newsapi.org/v2/top-headlines?country=pl&q=fingerstyle&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateTopHeadlinesUrlWithoutCountry() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.TOP_HEADLINES, null, Category.bussiness, 1, "fingerstyle").
                equals("https://newsapi.org/v2/top-headlines?category=bussiness&q=fingerstyle&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateTopHeadlinesUrlWithoutCountryCategoryAndPhrase() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.TOP_HEADLINES, null, null, 1, null).
                equals("https://newsapi.org/v2/top-headlines?page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateTopHeadlinesUrlWithoutCountryAndCategory() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.TOP_HEADLINES, null, null, 1, "fingerstyle").
                equals("https://newsapi.org/v2/top-headlines?q=fingerstyle&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateTopHeadlinesUrlWithoutCategoryAndPhrase() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.TOP_HEADLINES, Country.pl, null, 1, null).
                equals("https://newsapi.org/v2/top-headlines?country=pl&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateTopHeadlinesUrlWithoutCountryAndPhrase() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.TOP_HEADLINES, null, Category.bussiness, 1, null).
                equals("https://newsapi.org/v2/top-headlines?category=bussiness&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateEverythingUrlWithoutPhrase() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.EVERYTHING, Country.pl, Category.bussiness, 1, null).
                equals("https://newsapi.org/v2/everything?country=pl&category=bussiness&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateEverythingUrlWithoutCategory() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.EVERYTHING, Country.pl, null, 1, "fingerstyle").
                equals("https://newsapi.org/v2/everything?country=pl&q=fingerstyle&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateEverythingUrlWithoutCountry() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.EVERYTHING, null, Category.bussiness, 1, "fingerstyle").
                equals("https://newsapi.org/v2/everything?category=bussiness&q=fingerstyle&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateEverythingUrlWithoutCountryCategoryAndPhrase() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.EVERYTHING, null, null, 1, null).
                equals("https://newsapi.org/v2/everything?page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateEverythingUrlWithoutCountryAndCategory() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.EVERYTHING, null, null, 1, "fingerstyle").
                equals("https://newsapi.org/v2/everything?q=fingerstyle&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateEverythingUrlWithoutCategoryAndPhrase() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.EVERYTHING, Country.pl, null, 1, null).
                equals("https://newsapi.org/v2/everything?country=pl&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }

    @Test
    public void shouldGenerateEverythingUrlWithoutCountryAndPhrase() throws Exception {
        articleService = new ArticleService(restTemplateBuilder);
        assertTrue(articleService.generateUrl(ApiEndpoint.EVERYTHING, null, Category.bussiness, 1, null).
                equals("https://newsapi.org/v2/everything?category=bussiness&page=1&apiKey=7ad321a024144737968ec200d409dc99"));
    }
}