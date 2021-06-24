package com.example.springboot.html;

import java.util.ArrayList;
import java.util.List;

public class HtmlTag {
    private final String tagName;
    private HtmlAttributes attributes;
    private List<HtmlTag> contents;

    public HtmlTag(String tagName) {
        this.tagName = tagName;
        this.attributes = new HtmlAttributes();
        this.contents = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(toStringStartTag());
        contents.stream().map(HtmlTag::toString).forEach(sb::append);
        sb.append(toStringEndTag());
        return sb.toString();
    }

    private String toStringEndTag() {
        StringBuilder sb = new StringBuilder();
        sb.append("</").append(tagName).append(">\n");
        return sb.toString();
    }

    private String toStringStartTag() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tagName);
        sb.append(attributes.toString());
        sb.append(">\n");
        return sb.toString();
    }

    public void addAttr(String name, String value) {
        attributes.add(name, value);
    }

    public void addAttr(String name) {
        attributes.add(name);
    }

    public void addClass(String value) {
        attributes.add("class", value);
    }

    public void addContent(HtmlTag content) {
        this.contents.add(content);
    }

    public void addContent(List<HtmlTag> contents) {
        this.contents.addAll(contents);
    }

    public HtmlTag createContent(HtmlTag content) {
        addContent(content);
        return content;
    }

    public HtmlTag with(HtmlTag content) {
        this.contents.add(content);
        return this;
    }

    public HtmlTag with(String content) {
        return with(new HtmlNoTag(content));
    }

    public HtmlTag clazz(String value) {
        addClass(value);
        return this;
    }

    public HtmlTag as(String name, String value) {
        attributes.add(name, value);
        return this;
    }

    public HtmlTag as(String name) {
        attributes.add(name);
        return this;
    }
}