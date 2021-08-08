package com.example.application.petrzalka.component;

import com.example.application.petrzalka.html.AHref;
import com.example.application.petrzalka.html.HtmlTag;

public class Figure extends HtmlTag {

    private final String source;

    public Figure(String source) {
        super("figure");
        this.source = source;
    }

    @Override
    public String toString() {
        addContent(new AHref("", source, new HtmlTag("img").as("src", source)));
        return super.toString();
    }
}
