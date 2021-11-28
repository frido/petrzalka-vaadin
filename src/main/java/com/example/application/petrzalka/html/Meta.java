package com.example.application.petrzalka.html;

public class Meta extends HtmlTag {

    public Meta(String name, String content) {
        super("meta");
        addAttr("name", name);
        addAttr("content", content);
    }


}
