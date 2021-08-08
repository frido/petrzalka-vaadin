package com.example.application.petrzalka.html;

import java.util.HashMap;
import java.util.Map;

public class HtmlAttributes {
    private Map<String, Attribute> attrs;

    public HtmlAttributes() {
        attrs = new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        attrs.values().stream().map(Attribute::toString).forEach(sb::append);
        return sb.toString();
    }

    public void add(String name, String value) {
        Attribute attr = attrs.getOrDefault(name, new MultiAttribute(name));
        attr.add(value);
        attrs.put(name, attr);
    }

    public void add(String name) {
        Attribute attr = attrs.getOrDefault(name, new SingleAttribute(name));
        attrs.put(name, attr);
    }
}
