package com.example.application.views.about;

import com.example.application.services.BudgetService3;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.provider.DataProvider;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GridBuilder<T> {

    private final ColumnBuilderFactory columnBuilderFactory;
    private final GridConfig2<T> conf;
    private final EntityManager em;
    private final BudgetService3 service;
    private final FieldBuilderFactory fieldBuilderFactory;

    public GridBuilder(GridConfig2<T> conf, EntityManager em, BudgetService3 service) {
        this.conf = conf;
        this.em = em;
        this.service = service;
        this.columnBuilderFactory = new ColumnBuilderFactory();
        this.fieldBuilderFactory = new FieldBuilderFactory(service);
    }

    public Grid<T> buildGrid() {
        Grid<T> grid = new Grid<>(conf.getClazz(), false);
        conf.getProperties().forEach(property -> columnBuilderFactory.build(property, grid));
        grid.setDataProvider(getDataProvider());
        return grid;
    }

    public Class<T> getClazz() {
        return conf.getClazz();
    }

    private DataProvider<T,?> getDataProvider() {
        return new EntityDataProvider<>(em, conf.getClazz());
    }

    public BeanValidationBinder<T> buildBinder() {
        return new BeanValidationBinder<>(conf.getClazz());
    }


    public List<Component> buildComponents(BeanValidationBinder<T> binder) {
        return conf.getProperties().stream()
                .map(property -> fieldBuilderFactory.build(property, binder))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
