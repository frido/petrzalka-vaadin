package com.example.application.old.html;

public class AHref extends HtmlTag {
    public AHref(String clazz, String href, HtmlTag content) {
        super("a");
        addAttr("class", clazz);
        addAttr("href", href);
        if (href.startsWith("http")) {
            addAttr("target", "blank");
        }
        addContent(content);
    }

    public AHref(String href, String content) {
        this("", href, new HtmlNoTag(content));
    }
}
