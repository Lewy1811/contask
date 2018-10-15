package com.contask.controller;

import com.contask.model.*;
import com.contask.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Api(tags = "Request Controller")
@RestController
@RequestMapping(value = "/news")
public class RequestController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    ArticlesResponse articlesResponse;

    @ApiOperation(value = "Gets news by country and category filters")
    @GetMapping(value = "/{country}/{category}")
    public ArticlesResponse getArticles(@PathVariable("country") @ApiParam(value="The 2-letter ISO 3166-1 code of the country you want to get headlines for. Possible options: ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za") Country country,
                                        @PathVariable("category") @ApiParam(value = "The category you want to get headlines for. Possible options: business entertainment general health science sports technology") Category category) {
        ServiceResponse serviceResponse = articleService.getResponse(country, category);
        articlesResponse.setCountry(country);
        articlesResponse.setCategory(category);
        serviceResponse.getArticles().forEach(a -> articlesResponse.getArticles().add(new ArticleAdapter(a)));
        return articlesResponse;
    }

    @ApiOperation(value = "Returns all available categories for requests")
    @GetMapping(value = "/categories")
    public List<Category> getAvailableCategories() {
        return Arrays.asList(Category.values());
    }
}