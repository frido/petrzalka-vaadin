package com.example.application.views.about;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.ValidationException;

public class EntityView<T> extends Div {

    private Grid<T> grid;
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private transient GridConfig<T> conf;
    EntityService<T, Integer> entityService;

    private transient T selectedEntity;

    public EntityView(EntityService<T, Integer> entityService, GridConfig<T> conf) {
        this.entityService = entityService;
        this.conf = conf;
        grid = new Grid<>(conf.getClazz(), false);

        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        conf.getProperties().forEach(property -> 
            grid.addColumn(property.getValueProvider())
                .setHeader(property.getName())
                .setAutoWidth(true)
            );

        grid.setDataProvider(entityService);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                selectItem(event.getValue());
            } else {
                clearForm();
            }
        });

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.selectedEntity == null) {
                    Class<T> clazz = conf.getClazz();
                    Constructor<T> ctor = clazz.getConstructor();
                    T object = ctor.newInstance();
                    this.selectedEntity = object;
                }

                conf.getBinder().writeBean(this.selectedEntity);

                entityService.save(this.selectedEntity);
                clearForm();
                refreshGrid();
                Notification.show("SamplePerson details stored.");
                UI.getCurrent().navigate(AboutViewMain.class);
            } catch (ValidationException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException validationException) {
                Notification.show("An exception happened while trying to store the samplePerson details.");
            }
        });
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }


    private void populateForm(T value) {
        this.selectedEntity = value;
        conf.getBinder().readBean(this.selectedEntity);
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        
        for (Component field : conf.getComponents()) {
            ((HasStyle) field).addClassName("full-width");
        }
        conf.getComponents().forEach(formLayout::add);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");

        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("w-full flex-wrap bg-contrast-5 py-s px-l");
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    public void selectItem(T item) {
        populateForm(item);
    }

}
