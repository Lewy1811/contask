package com.contask.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticlesResponse {

    private Country country;
    private Category category;
    private List<ArticleAdapter> articles = new ArrayList<>();
}
