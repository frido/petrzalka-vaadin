package com.example.springboot.html;

public class Header extends HtmlTag{

    public Header(String clazz) {
        super("header");
        addAttr("class", clazz);
    }
}
