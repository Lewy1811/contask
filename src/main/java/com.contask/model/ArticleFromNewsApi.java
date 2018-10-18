package com.contask.model;

import lombok.Data;

@Data
public class ArticleFromNewsApi {

    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
}
