package com.example.springboot.page.project;

import static com.example.springboot.component.CssClass.*;

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
