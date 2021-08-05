package com.example.application.views.about;

import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.Binder.BindingBuilder;

public class PokusConfig implements IPokusConfig<Integer> {

    Validator<Integer> validator = new IntegerValidator();

    @Override
    public <E> void apply(BindingBuilder<E, String> bb) {
        bb.withConverter(new IntegerConverter()).withValidator(getValidator()).bind("test");
    }

    public Validator<Integer> getValidator() {
        return validator;
    }

}
