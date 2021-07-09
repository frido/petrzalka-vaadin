package com.example.application.old.page.project;

import com.example.application.old.model.IndexedEnum;

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
