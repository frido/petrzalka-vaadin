package com.example.springboot.html;

public class Link extends HtmlTag{

    public Link(String href, String rel) {
        super("link");
        addAttr("rel", rel);
        addAttr("href", href);
    }


}
