package com.example.application.views.about;

import java.util.Optional;

import com.example.application.old.page.budget.Budget;
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

public class EntityView extends Div {

    private Grid<Budget> grid = new Grid<>(Budget.class, false);
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    GridConfig<Budget> conf;
    EntityService<Budget, Integer> entityService;

    private Budget samplePerson;

    public EntityView(EntityService<Budget, Integer> entityService, GridConfig<Budget> conf) {
        this.entityService = entityService;
        this.conf = conf;

        setSizeFull();
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

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
                selectItem(event.getValue().getId());
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
                if (this.samplePerson == null) {
                    this.samplePerson = new Budget();
                }
                System.out.println("1");
                System.out.println(this.samplePerson);
                conf.getBinder().writeBean(this.samplePerson);
                System.out.println("2");
                System.out.println(this.samplePerson);

                // budgetService.update2(this.samplePerson); TODO: save entity
                clearForm();
                refreshGrid();
                Notification.show("SamplePerson details stored.");
                UI.getCurrent().navigate(AboutView.class);
            } catch (ValidationException validationException) {
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


    private void populateForm(Budget value) {
        this.samplePerson = value;
        conf.getBinder().readBean(this.samplePerson);
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
        wrapper.setWidthFull();

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

    public void selectItem(int id) {
        Optional<Budget> samplePersonFromBackend = entityService.get(id);
        if (samplePersonFromBackend.isPresent()) {
            populateForm(samplePersonFromBackend.get());
        } else {
            Notification.show(
                    String.format("The requested samplePerson was not found, ID = %d", id), 3000,
                    Notification.Position.BOTTOM_START);
            refreshGrid();
        }
    }

}
