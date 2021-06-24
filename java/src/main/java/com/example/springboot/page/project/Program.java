package com.example.springboot.page.project;

import com.example.springboot.model.IndexedEnum;

public enum Program implements IndexedEnum {
    TEST(1);

    private final Integer id;

    Program(int i) {
        this.id = i;
    }

    @Override
    public int getValue() {
        return id;
    }
}
