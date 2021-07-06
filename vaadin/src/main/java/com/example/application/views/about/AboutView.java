package com.example.application.views.about;

import com.example.springboot.page.budget.Budget;
import com.example.springboot.page.budget.BudgetService;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
public class AboutView extends Div {

    private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "master-detail/%d/edit";

    private final BudgetService budgetService;
    private Grid<Budget> grid = new Grid<>(Budget.class, false);
    private BeanValidationBinder<Budget> binder;
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private TextField title;

    private Budget samplePerson;

    public AboutView(@Autowired BudgetService budgetService) {
        this.budgetService = budgetService;

        setSizeFull();
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("title").setWidth("200px");
        grid.addColumn("year").setAutoWidth(true);
        grid.addColumn("program").setAutoWidth(true);
        grid.addColumn("amountOriginal").setAutoWidth(true);
        grid.addColumn("amountUpdated").setAutoWidth(true);
        grid.addColumn("amountReal").setAutoWidth(true);
        grid.addColumn("comment").setAutoWidth(true);
        grid.addColumn("status").setAutoWidth(true);
        grid.addColumn("project").setAutoWidth(true);
        grid.addColumn("useAmountReal").setAutoWidth(true);
        grid.addColumn("showComment").setAutoWidth(true);

//        grid.setDataProvider(new CrudServiceDataProvider<>(samplePersonService));
        grid.setDataProvider(DataProvider.ofCollection(budgetService.findAllBudgeets()));

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(AboutView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Budget.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.samplePerson == null) {
                    this.samplePerson = new Budget();
                }
                binder.writeBean(this.samplePerson);

//                samplePersonService.update(this.samplePerson);
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
        binder.readBean(this.samplePerson);

    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        title = new TextField("First Name");
        Component[] fields = new Component[]{title};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
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

}
