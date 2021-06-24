package com.example.springboot.page.budget;

import com.example.springboot.component.CssClass;

public enum BudgetStatus {
    ERROR("<i class=\"far fa-stop-circle\"></i>", "Zrušené", CssClass.STATUS_ERROR) {
        @Override
        public void visit(BudgetStatusVisitor visitor) {
            visitor.viistError();
        }
    },
    INWORK("<i class=\"far fa-play-circle\"></i>", "Realizuje sa", CssClass.STATUS_INWORK) {
        @Override
        public void visit(BudgetStatusVisitor visitor) {
            visitor.visitInProgress();
        }
    },
    SUCCESS("<i class=\"far fa-check-circle\"></i>", "Zrealizované", CssClass.STATUS_SUCCESS) {
        @Override
        public void visit(BudgetStatusVisitor visitor) {
            visitor.visitSuccess();
        }
    },
    POSTPONE("<i class=\"far fa-arrow-alt-circle-right\"></i>", "Odložené", CssClass.POSTPONE) {
        @Override
        public void visit(BudgetStatusVisitor visitor) {
            visitor.visitPospone();
        }
    };

    private final String icon;
    private final String description;
    private final String clazz;

    BudgetStatus(String icon, String description, String clazz) {
        this.icon = icon;
        this.description = description;
        this.clazz = clazz;
    }

    public String getIcon() {
        return icon;
    }

    public String getDecription() {
        return description;
    }

    public String getClazz() {
        return clazz;
    }

    public abstract void visit(BudgetStatusVisitor visitor);
}
