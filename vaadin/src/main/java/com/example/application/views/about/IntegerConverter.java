package com.example.application.views.about;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class IntegerConverter implements Converter<String, Integer> {
    @Override
    public Result<Integer> convertToModel(String s, ValueContext valueContext) {
        try {
            int i = Integer.parseInt(s);
            return Result.ok(i);
        } catch (Exception e) {
            return Result.error("Integer.parseInt(s)");
        }
    }

    @Override
    public String convertToPresentation(Integer integer, ValueContext valueContext) {
        return integer.toString(); // TODO: localizer?
    }
}
