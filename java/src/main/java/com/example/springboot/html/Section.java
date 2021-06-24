package com.example.springboot.html;

public class Section extends HtmlTag{

    public Section() {
        super("section");
        addAttr("class", "section");
    }
}
