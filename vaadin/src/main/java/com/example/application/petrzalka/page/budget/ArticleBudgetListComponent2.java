package com.example.application.petrzalka.page.budget;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import com.example.application.petrzalka.html.HtmlTag;

public class ArticleBudgetListComponent2 extends HtmlTag {

    private final List<BudgetProject> project;
    private int year;

    public ArticleBudgetListComponent2(List<BudgetProject> project, int year) {
        super("div");
        this.project = project;
        this.year = year;
    }

    @Override
    public String toString() {
        project.stream().sorted(Comparator.comparing(this::comparator).reversed()).forEach(p -> addContent(new ArticleBudgetComponent(p, year)));
        return super.toString();
    }

    private BigDecimal comparator(BudgetProject t) {
        return t.getAmount(year);
    }
}
