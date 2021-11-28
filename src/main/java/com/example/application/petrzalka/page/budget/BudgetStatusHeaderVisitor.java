package com.example.application.petrzalka.page.budget;

import com.example.application.petrzalka.component.Amount;
import com.example.application.petrzalka.html.Div;
import com.example.application.petrzalka.html.HtmlTag;

public class BudgetStatusHeaderVisitor implements BudgetStatusVisitor {
    /**
     *
     */
    private static final String TEXT_RIGHT = "text-right";
    private final HtmlTag segment;
    private final BudgetListDto list;

    public BudgetStatusHeaderVisitor(HtmlTag segment, BudgetListDto list) {
        this.segment = segment;
        this.list = list;
    }

    @Override
    public void visitSuccess() {
        BudgetStatus status = BudgetStatus.SUCCESS;
        HtmlTag row = createRow(status);
        row(row,2, description(status));
        row(row,2, projectCount(list.size()));
        row(row,8, "Výdavky: " + Amount.of(list.calculateRealAmount())).clazz(TEXT_RIGHT);
    }

    private String description(BudgetStatus status) {
        return status.getDecription() + " " + status.getIcon();
    }

    @Override
    public void visitInProgress() {
        BudgetStatus status = BudgetStatus.INWORK;
        HtmlTag row = createRow(status);
        row(row,2, description(status));
        row(row,2, projectCount(list.size()));
        row(row,4, "Aktuálne výdavky: " + Amount.of(list.calculateRealAmount()));
        row(row,4, "Plánované výdavky: " + Amount.of(list.calculateOriginalAmount())).clazz(TEXT_RIGHT);
    }

    @Override
    public void visitPospone() {
        BudgetStatus status = BudgetStatus.POSTPONE;
        HtmlTag row = createRow(status);
        row(row,2, description(status));
        row(row,2, projectCount(list.size())).clazz(TEXT_RIGHT); // TODO: last child in row as text-right: do it in css
    }

    @Override
    public void viistError() {
        BudgetStatus status = BudgetStatus.ERROR;
        HtmlTag row = createRow(status);
        row(row,2, description(status));
        row(row,2, projectCount(list.size())).clazz(TEXT_RIGHT);
    }

    private HtmlTag row(HtmlTag row, int columnSize, String text) {
        HtmlTag div = new Div("col-md-" + columnSize).with(text);
        row.with(div);
        return div;
    }

    private HtmlTag createRow(BudgetStatus status) {
        return segment.createContent(new Div("row status-text").clazz(status.getClazz()));
    }

    private String projectCount(int count) {
        if (count == 1) {
            return "1 projekt";
        }
        if (count >1 && count < 5)  {
            return count + " projekty";
        }
        return count + " projektov";
    }
}
