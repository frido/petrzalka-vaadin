package com.example.springboot.html;

public class Small extends HtmlTag{

    public Small() {
        super("small");
    }

    public Small(String content) {
        this();
        with(content);
    }
}
