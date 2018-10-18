package com.contask.model;

public enum ApiEndpoint {
    TOP_HEADLINES("/v2/top-headlines"), EVERYTHING("/v2/everything"), SOURCES("/v2/sources");

    private String urlSubstring;

    private ApiEndpoint(String urlName) {this.urlSubstring = urlName;}

    public String getUrlSubstring() {
        return urlSubstring;
    }
}