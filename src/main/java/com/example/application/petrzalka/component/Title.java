package com.example.application.petrzalka.component;

import com.example.application.petrzalka.html.H;
import com.example.application.petrzalka.html.HtmlNoTag;
import com.example.application.petrzalka.html.HtmlTag;

public class Title extends HtmlTag {

    private final HtmlNoTag text;

    public Title(String title) {
        super("header");
        this.text = new HtmlNoTag(title);
    }

    @Override
    public String toString() {
        addContent(new H(1, "", text));
        return super.toString();
    }
}
