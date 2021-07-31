package com.example.application.views.about;

import com.example.application.services.EntityService;
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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityView2<E> extends Div {

    private final GridBuilder<E> gridBuilder;
    BeanValidationBinder<E> binder;
    private final EntityService service;
    private Grid<?> grid;
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private transient E selectedEntity;

    public  EntityView2(GridBuilder<E> gridBuilder, EntityService service) {
        this.gridBuilder = gridBuilder;
        this.grid = gridBuilder.buildGrid();
        this.binder = gridBuilder.buildBinder();
        this.service = service;
        SplitLayout splitLayout = new SplitLayout();
        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);
        add(splitLayout);

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                selectItem((E) event.getValue());
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
                    Class<E> clazz = gridBuilder.getClazz();
                    Constructor<E> ctor = clazz.getConstructor();
                    E object = ctor.newInstance();
                    this.selectedEntity = object;
                }

                binder.writeBean(this.selectedEntity);

                service.save(this.selectedEntity);
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


    private void populateForm(E value) {
        this.selectedEntity = value;
        binder.readBean(this.selectedEntity);
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();

        for (Component field : gridBuilder.buildComponents(binder)) {
            ((HasStyle) field).addClassName("full-width");
            formLayout.add(field);
        }

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

    public void selectItem(E item) {
        populateForm(item);
    }

}
