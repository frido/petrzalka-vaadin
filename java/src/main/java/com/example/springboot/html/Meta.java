package com.example.springboot.html;

public class Meta extends HtmlTag{

    public Meta(String name, String content) {
        super("meta");
        addAttr("name", name);
        addAttr("content", content);
    }


}
