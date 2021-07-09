package com.example.application.old.html;

public class Header extends HtmlTag {

    public Header(String clazz) {
        super("header");
        addAttr("class", clazz);
    }
}
