package com.example.springboot.html;

public class Row extends HtmlTag{

    public Row() {
        super("div");
        addAttr("class", "row");
    }

    public HtmlTag column(String clazz) {
        HtmlTag column = new Div(clazz);
        addContent(column);
        return column;
    }
}
