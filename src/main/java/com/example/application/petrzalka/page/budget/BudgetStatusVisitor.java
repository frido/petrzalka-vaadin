package com.example.application.petrzalka.page.budget;

public interface BudgetStatusVisitor {
    void visitSuccess();
    void visitInProgress();
    void visitPospone();
    void viistError();
}
