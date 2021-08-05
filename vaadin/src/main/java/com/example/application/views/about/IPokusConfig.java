package com.example.application.views.about;

import com.vaadin.flow.data.binder.Binder.BindingBuilder;

public interface IPokusConfig<T> {
    public <E> void apply(BindingBuilder<E, String> bb);
}
