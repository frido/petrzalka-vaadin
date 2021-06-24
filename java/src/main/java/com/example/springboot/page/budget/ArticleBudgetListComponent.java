package com.example.springboot.page.budget;

import com.example.springboot.component.Amount;
import com.example.springboot.html.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArticleBudgetListComponent extends HtmlTag {
    private final BudgetByYearDto plan;
    private final boolean showSummary;

    public ArticleBudgetListComponent(BudgetByYearDto plan, boolean showSummary) {
        super("div");
        this.plan = plan;
        this.showSummary = showSummary;
    }

    @Override
    public String toString() {
        if (showSummary) {
            List<BudgetStatus> statusList = Arrays.asList(BudgetStatus.SUCCESS, BudgetStatus.INWORK, BudgetStatus.POSTPONE, BudgetStatus.ERROR);
            statusList.stream().map(this::segment).filter(Objects::nonNull).forEach(this::addContent);
        } else {
            plan.getBudgetList().stream().map(this::article).forEach(this::addContent);
        }
        return super.toString();
    }

    private HtmlTag segment(BudgetStatus status) {
        HtmlTag segment = new Section();
        BudgetListDto listByStatus = plan.getBudgetListByStatus(status);
        if (listByStatus.size() == 0) {
            return null;
        }
        status.visit(new BudgetStatusHeaderVisitor(segment, plan.getBudgetListByStatus(status)));
        segment.addContent(listByStatus.list().stream().map(this::article).collect(Collectors.toList()));
        return segment;
    }

    private HtmlTag article(Budget plan) {
        HtmlTag article = new HtmlTag("article");
        article.clazz("box").clazz(plan.getStatus().getClazz());
        Row row = new Row();
        row.column("col-md-9").with(new H(3, null, plan.getTitle()));
        row.column("col-md-3 text-right")
            .with(new Span("muted", "rozpočet"))
            .with(new Span("amount", Amount.of(plan.getAmountOriginal())));
        article.addContent(row);
        return article;
    }
}
