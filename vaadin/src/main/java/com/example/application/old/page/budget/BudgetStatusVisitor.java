package com.example.application.old.page.budget;

public interface BudgetStatusVisitor {
    void visitSuccess();
    void visitInProgress();
    void visitPospone();
    void viistError();
}
