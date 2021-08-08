package com.example.application.petrzalka.page.project;

public enum Program {
    TEST(1);

    private final Integer id;

    Program(int i) {
        this.id = i;
    }

    public int getValue() {
        return id;
    }
}
