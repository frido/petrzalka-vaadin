package com.example.application.petrzalka.html;

public class Small extends HtmlTag {

    public Small() {
        super("small");
    }

    public Small(String content) {
        this();
        with(content);
    }
}
