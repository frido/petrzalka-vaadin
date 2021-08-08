package com.example.application.petrzalka.page.project;

import static com.example.application.petrzalka.component.CssClass.BUDGET_DONE;
import static com.example.application.petrzalka.component.CssClass.BUDGET_PLANED;

public enum ProjectStatus {
    INWORK("prebieha", BUDGET_PLANED),
    DONE("hotovo", BUDGET_DONE);

    private final String label;
    private final String status;

    ProjectStatus(String label, String status) {
        this.label = label;
        this.status = status;
    }

    public String clazz() {
        return status;
    }

    public String label() {
        return label;
    }
}
