package com.example.springboot.html;

public class Html extends HtmlTag{

    public Html(String lang) {
        super("html");
        addAttr("lang", lang);
    }


}
