package com.example.application.views.editor;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class IntegerValidator implements Validator<Integer> {

    @Override
    public ValidationResult apply(Integer value, ValueContext context) {
        return ValidationResult.ok();
    }
    
}
