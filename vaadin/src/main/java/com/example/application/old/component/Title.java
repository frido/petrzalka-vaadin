package com.example.application.old.component;

import com.example.application.old.html.H;
import com.example.application.old.html.HtmlNoTag;
import com.example.application.old.html.HtmlTag;

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
