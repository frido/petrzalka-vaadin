package com.example.application.petrzalka.html;

public class MetaProperty extends HtmlTag {

    public MetaProperty(String name, String property) {
        super("meta");
        addAttr("name", name);
        addAttr("property", property);
    }


}
