package com.example.springboot.core;

import com.example.springboot.component.Amount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AmountTest {
    @Test
    public void test() {
        BigDecimal value = new BigDecimal(111111.254);
        System.out.println(Amount.of(value));
    }
}