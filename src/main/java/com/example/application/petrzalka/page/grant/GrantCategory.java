package com.example.application.petrzalka.page.grant;

public enum GrantCategory {
    OZ("Dotácie"),
    SPORT("Športové granty");

    private final String label;

    GrantCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
