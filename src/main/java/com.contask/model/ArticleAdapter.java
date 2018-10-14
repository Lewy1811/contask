package com.contask.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.format.DateTimeFormatter;

@JsonPropertyOrder({"author", "title", "description", "date", "sourceName", "articleUrl", "imageUrl"})
public class ArticleAdapter implements Article {

    private final ArticleFromNewsApi articleFromNewsApi;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ArticleAdapter(ArticleFromNewsApi articleFromNewsOrg) {
        this.articleFromNewsApi = articleFromNewsOrg;
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
        return articleFromNewsApi.getPublishedAt().format(dateTimeFormatter);
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