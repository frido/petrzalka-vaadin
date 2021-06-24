package com.example.springboot.component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Amount {
    private static final NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("sk"));
    private final BigDecimal value;

    private Amount(BigDecimal value) {
        this.value = value;
    }

    public static String of(BigDecimal value) {
        Amount amount = new Amount(value.setScale(2, RoundingMode.HALF_DOWN));
        return amount.toString();
    }

    @Override
    public String toString() {
        return nf.format(value) + " €";
    }
}
