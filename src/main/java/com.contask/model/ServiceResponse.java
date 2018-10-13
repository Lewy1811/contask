package com.contask.model;

import com.contask.model.Article;
import lombok.Data;

import java.util.List;

@Data
public class ServiceResponse {

    private String status;
    private String totalResults;
    private List<Article> articles;
}