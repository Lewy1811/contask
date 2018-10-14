package com.contask.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ArticlesResponse {

    private String country;
    private String category;
    private List<ArticleAdapter> articles = new ArrayList<>();
}
