package com.example.springboot.component;

public class Base {

    private static String base = ""; // TODO: configurable

    public static String resolve(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        return base + url;
    }

    public static String resolve(String url, String name) {
        return base + url + "/" + name + "/";
    }
}
