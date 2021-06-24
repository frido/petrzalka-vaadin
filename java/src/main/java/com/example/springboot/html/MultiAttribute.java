package com.example.springboot.html;

public class MultiAttribute extends SingleAttribute {
    private String value;

    public MultiAttribute(String name) {
        super(name);
    }

    public void add(String value) {
        if(this.value == null || this.value.isEmpty()) {
            this.value = value;
        } else {
            this.value = this.value + " " + value;
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(" ")
                .append(name)
                .append("=\"")
                .append(value)
                .append("\"")
                .toString();
    }
}
