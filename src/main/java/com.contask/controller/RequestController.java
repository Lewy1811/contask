package com.contask.controller;

import com.contask.model.ArticleAdapter;
import com.contask.model.EndpointObject;
import com.contask.service.ArticleService;
import com.contask.model.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/news")
public class RequestController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    EndpointObject endpointObject;

    @GetMapping(value = "/{country}/{category}")
    public EndpointObject getArticles(@PathVariable("country") String country, @PathVariable("category") String category) {
        ServiceResponse serviceResponse = articleService.getResponse(country, category);
        endpointObject.setCountry(country);
        endpointObject.setCategory(category);
        serviceResponse.getArticles().forEach(a -> endpointObject.getArticles().add(new ArticleAdapter(a)));
        return endpointObject;
    }
}
