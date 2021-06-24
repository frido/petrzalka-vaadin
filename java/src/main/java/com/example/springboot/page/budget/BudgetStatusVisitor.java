package com.example.springboot.page.budget;

public interface BudgetStatusVisitor {
    void visitSuccess();
    void visitInProgress();
    void visitPospone();
    void viistError();
}
