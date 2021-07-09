package com.example.application.views.helloworld;

import com.example.application.old.Configuration;
import com.example.application.old.MainRunner;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Hello World")
public class HelloWorldView extends Div {

    @Autowired
    MainRunner runner;

    private TextField outDirField;
    private ComboBox<String> baseField;
    private Button generateBtn;

    public HelloWorldView() {
        addClassName("hello-world-view");
        outDirField = new TextField("Output Dir");
        baseField = new ComboBox<>("Base");
        baseField.setItems(Arrays.asList("", "C:\\home\\repos\\frido.github.io"));
        generateBtn = new Button("Generate");

        Configuration config = new Configuration();
        Binder<Configuration> binder = new Binder<>(Configuration.class);
        binder.forField(baseField).bind(Configuration::getBase, Configuration::setBase);
        binder.forField(outDirField).bind(Configuration::getOutputDir, Configuration::setOutputDir);
        binder.setBean(config);

        FormLayout formLayout = new FormLayout();
        formLayout.add(outDirField, baseField, generateBtn);
        add(formLayout);

        generateBtn.addClickListener(e -> {
            try {
                runner.run(config);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            Notification.show("Hello Generated");
        });
    }

}
