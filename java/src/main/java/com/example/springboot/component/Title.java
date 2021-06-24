package com.example.springboot.component;

import com.example.springboot.html.H;
import com.example.springboot.html.HtmlNoTag;
import com.example.springboot.html.HtmlTag;

public class Title extends HtmlTag {

    private final HtmlNoTag title;

    public Title(String title) {
        super("header");
        this.title = new HtmlNoTag(title);
    }

    @Override
    public String toString() {
        addContent(new H(1, "", title));
        return super.toString();
    }
}
