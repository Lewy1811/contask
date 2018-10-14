package com.contask.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleFromNewsApi {

    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private LocalDateTime publishedAt;
    private String content;
}
