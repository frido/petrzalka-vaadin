package com.example.springboot.html;

import com.example.springboot.component.Base;

public class AHref extends HtmlTag {
    public AHref(String clazz, String href, HtmlTag content) {
        super("a");
        addAttr("class", clazz);
        addAttr("href", Base.resolve(href));
        if (href.startsWith("http")) {
            addAttr("target", "blank");
        }
        addContent(content);
    }

    public AHref(String href, String content) {
        this("", href, new HtmlNoTag(content));
    }
}
