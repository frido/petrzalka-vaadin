package com.example.springboot.component;

import com.example.springboot.html.AHref;
import com.example.springboot.html.H;
import com.example.springboot.html.HtmlNoTag;
import com.example.springboot.html.HtmlTag;

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
