package com.example.application.views.about;

import com.example.springboot.page.budget.*;
import com.example.springboot.page.project.Program;
import com.example.springboot.page.project.Project;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@PageTitle("About")
@Route(value = "about/:samplePersonID?/:action?(edit)", layout = MainView.class)
public class AboutView extends Div implements BeforeEnterObserver {

    private final String SAMPLEPERSON_ID = "samplePersonID";
    private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "about/%d/edit";

    private final BudgetService budgetService;
    private Grid<BudgetDto> grid = new Grid<>(BudgetDto.class, false);
    private BeanValidationBinder<BudgetDto> binder;
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private TextField title;
    private TextField year;
    private ComboBox<Program> program;
    private TextField amountOriginal;
    private TextField amountUpdated;
    private TextField amountReal;
    private TextField comment;
    private ComboBox<BudgetStatus> status;
    private ComboBox<BudgetProject> project;
    private Checkbox useAmountReal;
    private Checkbox showComment;

    private BudgetDto samplePerson;

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
        binder = new BeanValidationBinder<>(BudgetDto.class);

        // Bind fields. This where you'd define e.g. validation rules
//        binder.bindInstanceFields(this);
        binder.forField(title).bind("title");
        binder.forField(year).withConverter(new IntegerConverter()).bind("year");
        binder.forField(program).bind("program");
        binder.forField(amountOriginal).withConverter(new DecimalConverter()).bind("amountOriginal");
        binder.forField(amountUpdated).withConverter(new DecimalConverter()).bind("amountUpdated");
        binder.forField(amountReal).withConverter(new DecimalConverter()).bind("amountReal");
        binder.forField(comment).bind("comment");
        binder.forField(status).bind("status");
        binder.forField(project).bind("project");
        binder.forField(useAmountReal).bind("useAmountReal");
        binder.forField(showComment).bind("showComment");

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.samplePerson == null) {
                    this.samplePerson = new BudgetDto();
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


    private void populateForm(BudgetDto value) {
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
        title = new TextField("title");
        year = new TextField("year");
        program = new ComboBox<>("program");
        program.setItems(Program.values());
        amountOriginal = new TextField("amountOriginal");
        amountUpdated = new TextField("amountUpdated");
        amountReal = new TextField("amountReal");
        comment = new TextField("comment");
        status = new ComboBox<>("status");
        status.setItems(BudgetStatus.values());
        project = new ComboBox<>("project");
//        project.setDataProvider(); TODO: lazy
        useAmountReal = new Checkbox("useAmountReal");
        showComment = new Checkbox("showComment");
        Component[] fields = new Component[]{title, year, program, amountOriginal,
                amountUpdated, amountReal, comment, status, project, useAmountReal,
                showComment};

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

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Integer> samplePersonId = event.getRouteParameters().getInteger(SAMPLEPERSON_ID);
        if (samplePersonId.isPresent()) {
            Optional<BudgetDto> samplePersonFromBackend = budgetService.get(samplePersonId.get());
            if (samplePersonFromBackend.isPresent()) {
                populateForm(samplePersonFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested samplePerson was not found, ID = %d", samplePersonId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(AboutView.class);
            }
        }
    }

}
