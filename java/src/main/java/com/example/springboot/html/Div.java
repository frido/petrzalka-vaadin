package com.example.springboot.html;

public class Div extends HtmlTag{

    public Div(String clazz) {
        super("div");
        addAttr("class", clazz);
    }

    public Div(String clazz, HtmlTag content) {
        this(clazz);
        addContent(content);
    }

    public Div(String clazz, String content) {
        this(clazz, new HtmlNoTag(content));
    }
}
