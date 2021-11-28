package com.example.application.petrzalka.html;

public class HtmlNoTag extends HtmlTag {
    private final String text;

    public HtmlNoTag(String text) {
        super(null);
        this.text = text;
    }

    @Override
    public String toString() {
        return text == null ? "" : text;
    }

}
