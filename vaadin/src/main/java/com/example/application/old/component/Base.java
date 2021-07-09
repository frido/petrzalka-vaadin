package com.example.application.old.component;

public class Base {

    private String base;

    public Base(String base) {
        this.base = base;
    }

    public String resolve(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        return base + url;
    }

    public String resolve(String url, String name) {
        return base + url + "/" + name + "/";
    }
}
