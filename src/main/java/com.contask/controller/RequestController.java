package com.contask.controller;

import com.contask.model.*;
import com.contask.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(tags = "Request Controller")
@RestController
@RequestMapping(value = "/news")
public class RequestController {

    @Autowired
    private ArticleService articleService;
    private final static int firstPageNumber = 1;


    @ApiOperation(value = "Returns all news by country and category filters")
    @GetMapping(value = "/{country}/{category}")
    public ArticlesResponse getArticles(@PathVariable("country") @ApiParam(value = "The 2-letter ISO 3166-1 code of the country you want to get headlines for. Possible options: AE AR AT AU BE BG BR CA CH CN CO CU CZ DE EG FR GB GR HK HU ID IE IL IN IT JP KR LT LV MA MX MY NG NL NO NZ PH PL PT RO RS RU SA SE SG SI SK TH TR TW UA US VE ZA") Country country,
                                        @PathVariable("category") @ApiParam(value = "The category you want to get headlines for. Possible options: BUSINESS entertainment general health science sports technology") Category category)
            throws IllegalArgumentException {

        ServiceResponse serviceResponse = articleService.getResponse(ApiEndpoint.TOP_HEADLINES, country, category, firstPageNumber, null);
        if (serviceResponse.getNumberOfPages() > 1) {
            preparePageIndexesStream(serviceResponse).parallelStream().
                    forEach(page -> serviceResponse.getArticles().addAll(articleService.getResponse(ApiEndpoint.TOP_HEADLINES, country, category, page, null).getArticles()));
        }
        ArticlesResponse articlesResponse = new ArticlesResponse();
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

    @ApiOperation(value = "Returns all news that include phrase String in title or content")
    @GetMapping(value = "/find")
    public ArticlesResponse getArticlesByPhrase(@RequestParam("phrase") @ApiParam(value = "Keywords or a phrase to search for") String phrase)
            throws IllegalArgumentException {

        ServiceResponse serviceResponse = articleService.getResponse(ApiEndpoint.TOP_HEADLINES,null, null, firstPageNumber, phrase);
        if (serviceResponse.getNumberOfPages() > 1) {
            preparePageIndexesStream(serviceResponse).parallelStream().
                    forEach(page -> serviceResponse.getArticles().addAll(articleService.getResponse(ApiEndpoint.TOP_HEADLINES,null, null, page, phrase).getArticles()));
        }
        ArticlesResponse articlesResponse = new ArticlesResponse();
        serviceResponse.getArticles().forEach(a -> articlesResponse.getArticles().add(new ArticleAdapter(a)));
        return articlesResponse;
    }

    private List<Integer> preparePageIndexesStream(ServiceResponse serviceResponse) {
        ArrayList<Integer> pages = new ArrayList<>();
        for (int i = 1; i < serviceResponse.getNumberOfPages(); i++) {
            pages.add(i + 1);
        }
        return pages;
    }
}