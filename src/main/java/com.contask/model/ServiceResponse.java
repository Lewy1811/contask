package com.contask.model;

import lombok.Data;

import java.util.List;

@Data
public class ServiceResponse {

    private String status;
    private Integer totalResults;
    private List<ArticleFromNewsApi> articles;

    public Integer getNumberOfPages() {
        if (totalResults != 0) {
            Integer result = totalResults / articles.size();
            if (totalResults % articles.size() > 0) {
                result = result + 1;
            }
            return result;
        } else {
            return 0;
        }
    }
}