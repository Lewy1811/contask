package com.contask.controller;

import com.contask.model.ArticleAdapter;
import com.contask.model.ArticlesResponse;
import com.contask.service.ArticleService;
import com.contask.model.ServiceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/{country}/{category}", description = "Main controller")
@RestController
@RequestMapping(value = "/news")
public class RequestController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    ArticlesResponse articlesResponse;

    @ApiOperation(value = "Finds news by country and category filters")
    @GetMapping(value = "/{country}/{category}")
    public ArticlesResponse getArticles(@PathVariable("country") String country, @PathVariable("category") String category) {
        ServiceResponse serviceResponse = articleService.getResponse(country, category);
        articlesResponse.setCountry(country);
        articlesResponse.setCategory(category);
        serviceResponse.getArticles().forEach(a -> articlesResponse.getArticles().add(new ArticleAdapter(a)));
        return articlesResponse;
    }
}