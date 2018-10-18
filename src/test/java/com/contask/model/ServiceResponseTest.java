package com.contask.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceResponseTest {

    private ServiceResponse serviceResponse = new ServiceResponse();
    private List<ArticleFromNewsApi> articles = new ArrayList<>();
    private ArticleFromNewsApi firstArticleFromNewsApi = new ArticleFromNewsApi();
    private ArticleFromNewsApi secondArticleFromNewsApi = new ArticleFromNewsApi();
    private ArticleFromNewsApi thirdArticleFromNewsApi = new ArticleFromNewsApi();

    @Before
    public void init() {
        articles.add(firstArticleFromNewsApi);
        articles.add(secondArticleFromNewsApi);
        articles.add(thirdArticleFromNewsApi);

        serviceResponse.setArticles(articles);
    }

    @Test
    public void shouldGetNumberOfPagesNoRestDivision() throws Exception {
        serviceResponse.setTotalResults(9);
        assertTrue(serviceResponse.getNumberOfPages().equals(3));
    }

    @Test
    public void shouldGetNumberOfPagesDivisionWithRest() throws Exception {
        serviceResponse.setTotalResults(10);
        assertTrue(serviceResponse.getNumberOfPages().equals(4));
    }

    @Test
    public void shouldReturnZero() throws Exception {
        serviceResponse.setTotalResults(0);
        assertTrue(serviceResponse.getNumberOfPages().equals(0));
    }
}