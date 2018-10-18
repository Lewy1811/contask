package com.contask.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@JsonPropertyOrder({"author", "title", "description", "date", "sourceName", "articleUrl", "imageUrl"})
public class ArticleAdapter implements Article {

    private final ArticleFromNewsApi articleFromNewsApi;
    //From newsapi.org - the date and time that the article was published are in UTC (+000)
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC);

    public ArticleAdapter(ArticleFromNewsApi articleFromNewApi) {
        this.articleFromNewsApi = articleFromNewApi;
    }

    @Override
    public String getAuthor() {
        return articleFromNewsApi.getAuthor();
    }

    @Override
    public String getTitle() {
        return articleFromNewsApi.getTitle();
    }

    @Override
    public String getDescription() {
        return articleFromNewsApi.getDescription();
    }

    @Override
    public String getDate() {
        return LocalDateTime.parse(articleFromNewsApi.getPublishedAt().substring(0 , 19)).format(dateTimeFormatter);
    }

    @Override
    public String getSourceName() {
        return articleFromNewsApi.getSource().getName();
    }

    @Override
    public String getArticleUrl() {
        return articleFromNewsApi.getUrl();
    }

    @Override
    public String getImageUrl() {
        return articleFromNewsApi.getUrlToImage();
    }
}