package com.example.springboot.page.budget;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public class BudgetListDto {
    private final List<Budget> list;

    public BudgetListDto(List<Budget> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public BigDecimal calculateOriginalAmount() {
        return calculateSum(Budget::getAmountOriginal);
    }

    public BigDecimal calculateRealAmount() {
        return calculateSum(Budget::getAmountReal);
    }

    public BigDecimal calculateUpdatedAmount() {
        return calculateSum(Budget::getAmountUpdated);
    }

    private BigDecimal calculateSum(Function<Budget, BigDecimal> mapper) {
        return list.stream().map(mapper).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Budget> list() {
        return list;
    }
}
