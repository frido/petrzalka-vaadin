package com.example.application.petrzalka.html;

public class Header extends HtmlTag {

    public Header(String clazz) {
        super("header");
        addAttr("class", clazz);
    }
}
