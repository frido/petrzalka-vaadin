package com.example.springboot.html;

public class Img extends HtmlTag {

    public Img(String src, String alt) {
        super("img");
        addAttr("src", src);
        addAttr("alt", alt);
    }
}
