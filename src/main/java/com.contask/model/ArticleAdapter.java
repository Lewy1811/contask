package com.contask.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleAdapter {

    private String author;
    private String title;
    private String description;
    private String date;
    private String sourceName;
    private String articleUrl;
    private String imageUrl;

    public ArticleAdapter(Article article) {
        this.author = article.getAuthor();
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.date = article.getPublishedAt().substring(0, 10);
        this.sourceName = article.getSource().getName();
        this.articleUrl = article.getUrl();
        this.imageUrl = article.getUrlToImage();
    }
}