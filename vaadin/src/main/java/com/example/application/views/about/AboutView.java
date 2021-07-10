package com.example.application.views.about;

import com.example.application.old.page.budget.*;
import com.example.application.old.page.project.Program;
import com.example.application.services.BudgetService2;
import com.example.application.views.main.MainView;
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
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;

import java.util.Optional;
import java.util.function.Function;

@PageTitle("About")
@Route(value = "about/:samplePersonID?/:action?(edit)", layout = MainView.class)
public class AboutView extends Div /*implements BeforeEnterObserver*/ {

    private final String SAMPLEPERSON_ID = "samplePersonID";
    private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "about/%d/edit";

    private final BudgetService budgetService;
    private final BudgetService2 samplePersonService;
    private Grid<Budget> grid = new Grid<>(Budget.class, false);
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

    public AboutView(@Autowired BudgetService budgetService, @Autowired BudgetService2 samplePersonService) {
        this.budgetService = budgetService;
        this.samplePersonService = samplePersonService;

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
        grid.addColumn(this.generalRenderer(Budget::getProject, BudgetProject::getTitle)).setAutoWidth(true);
        grid.addColumn("useAmountReal").setAutoWidth(true);
        grid.addColumn("showComment").setAutoWidth(true);

        // TODO: Service pouziva Budget, Grid zasa BudgetDto - data provider ktory to premapuje
        grid.setDataProvider(new CrudServiceDataProvider<>(samplePersonService));
//        grid.setDataProvider(DataProvider.ofCollection(budgetService.findAllBudgeets()));

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
//                UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
                selectItem(event.getValue().getId());
            } else {
                clearForm();
//                UI.getCurrent().navigate(AboutView.class);
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
                System.out.println("1");
                System.out.println(this.samplePerson);
                binder.writeBean(this.samplePerson);
                System.out.println("2");
                System.out.println(this.samplePerson);

                budgetService.update(this.samplePerson);
                clearForm();
                refreshGrid();
                Notification.show("SamplePerson details stored.");
                UI.getCurrent().navigate(AboutView.class);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the samplePerson details.");
            }
        });
    }

    private <S, T>ValueProvider<S, String> generalRenderer(Function<S, T> bean, Function<T, String> str) {
        return x -> str.apply(bean.apply(x));
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
        project.setItems(budgetService.findAll(BudgetProject.class)); //TODO: lazy
        project.setItemLabelGenerator(BudgetProject::getTitle);
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

//    @Override
//    public void beforeEnter(BeforeEnterEvent event) {
//        Optional<Integer> samplePersonId = event.getRouteParameters().getInteger(SAMPLEPERSON_ID);
//        if (samplePersonId.isPresent()) {
//            selectItem(samplePersonId.get());
//        }
//    }

    public void selectItem(int id) {
        Optional<BudgetDto> samplePersonFromBackend = budgetService.get(id);
        if (samplePersonFromBackend.isPresent()) {
            populateForm(samplePersonFromBackend.get());
        } else {
            Notification.show(
                    String.format("The requested samplePerson was not found, ID = %d", id), 3000,
                    Notification.Position.BOTTOM_START);
            // when a row is selected but the data is no longer available,
            // refresh grid
            refreshGrid();
//            event.forwardTo(AboutView.class);
        }
    }

}
