package com.example.application.old.html;

public class SingleAttribute implements Attribute { // TODO: better name
    protected final String name;

    public SingleAttribute(String name) {
        this.name = name;
    }

    public void add(String value) {
        // do nothing
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(" ")
                .append(name)
                .append(" ")
                .toString();

    }
}
