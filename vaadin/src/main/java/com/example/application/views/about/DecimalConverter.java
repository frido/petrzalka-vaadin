package com.example.application.views.about;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.math.BigDecimal;

public class DecimalConverter implements Converter<String, BigDecimal> {
    @Override
    public Result<BigDecimal> convertToModel(String s, ValueContext valueContext) {
        try {
            BigDecimal i = new BigDecimal(s);
            return Result.ok(i);
        } catch (Exception e) {
            return Result.error("new BigDecimal(s)");
        }
    }

    @Override
    public String convertToPresentation(BigDecimal newtarget, ValueContext valueContext) {
        return newtarget.toString();
    }
}
