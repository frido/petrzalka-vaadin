package com.example.springboot.page.budget;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetByYearDto {
    private final int year;
    private final List<Budget> list;

    public BudgetByYearDto(int year, List<Budget> list) {
        this.year = year;
        this.list = list; // store it as budgetListDto
    }

    public int getYear() {
        return year;
    }

    public List<Budget> getBudgetList() {
        return list;
    }

    public BudgetListDto getBudgetListByStatus(BudgetStatus status) {
        List<Budget> statusList = list.stream().filter(budget -> budget.getStatus().equals(status)).collect(Collectors.toList());
        return new BudgetListDto(statusList);
    }

    public BigDecimal getBudgetAmount() {
        return list.stream().map(Budget::getAmountOriginal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
