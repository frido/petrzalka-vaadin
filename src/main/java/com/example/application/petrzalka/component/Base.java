package com.example.application.petrzalka.component;

public class Base {

    private String baseUrl;

    public Base(String base) {
        this.baseUrl = base;
    }

    public String resolve(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        return baseUrl + url;
    }

    public String resolve(String url, String name) {
        return baseUrl + url + "/" + name + "/";
    }
}
