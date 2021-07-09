package com.example.application.old.page;

import com.example.application.old.Configuration;
import com.example.application.old.component.ContentProvider;
import com.example.application.old.component.Template;

public abstract class BasePage implements Page, ContentProvider, HeadProvider, PageHeaderProvider, ConfigurationProvider {

    protected Configuration configuration;

    protected BasePage (Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return getTemplate().toString();
    }

    @Override
    public String getTitle() {
        return "Projekty v Petržalke";
    }

    @Override
    public String getDescription() {
        return "Petržalka, investičný plán, projekty, rozpočet, štúdie, zámery";
    }

    @Override
    public String getIcon() {
        return "img/logo-m.png";
    }

    @Override
    public Configuration getConfig() {
        return configuration;
    }

    protected Template getTemplate() {
        return new Template(this, this,this, this);
    }
}
