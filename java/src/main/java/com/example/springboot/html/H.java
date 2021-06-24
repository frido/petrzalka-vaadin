package com.example.springboot.html;

public class H extends HtmlTag {
    public H(int header) {
        super("h" + header);
    }
    public H(int header, String clazz, String content) {
        super("h" + header);
        addAttr("class", clazz);
        addContent(new HtmlNoTag(content));
    }

    public H(int header, String clazz, HtmlTag content) {
        super("h" + header);
        addAttr("class", clazz);
        addContent(content);
    }
}
