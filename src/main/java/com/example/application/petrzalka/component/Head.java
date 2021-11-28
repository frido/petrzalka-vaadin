package com.example.application.petrzalka.component;

import com.example.application.petrzalka.html.HtmlTag;
import com.example.application.petrzalka.html.Link;
import com.example.application.petrzalka.html.Meta;
import com.example.application.petrzalka.html.MetaProperty;
import com.example.application.petrzalka.page.ConfigurationProvider;
import com.example.application.petrzalka.page.HeadProvider;

public class Head extends HtmlTag {

    private static final String STYLESHEET = "stylesheet";
    private static final String SCRIPT = "script";

    public Head(ConfigurationProvider configProvider, HeadProvider headProvider) {
        super("head");
        Base base = new Base(configProvider.getConfig().getBase());
        googleAnalytics();
        addContent(new HtmlTag("base").as("href", base.resolve("/")));
        addContent(new HtmlTag("title").with(headProvider.getTitle()));
        addContent(new HtmlTag("meta").as("charset", "utf-8"));
        addContent(new Meta("Description",headProvider.getDescription()));
        addContent(new Meta("viewport", "width=device-width, initial-scale=1.0"));
        addContent(new Meta("language", "sk"));
        addContent(new Meta("robots", "index,follow"));
        addContent(new Meta("googlebot", "index,follow"));
        addContent(new Meta("keywords", "Petržalka, investičný plán, projekty, rozpočet, štúdie, zámery"));
        addContent(new MetaProperty("og:url", ""));
        addContent(new MetaProperty("og:type", "article"));
        addContent(new MetaProperty("og:title", headProvider.getTitle()));
        addContent(new MetaProperty("og:description", headProvider.getDescription()));
        addContent(new MetaProperty("og:image", headProvider.getIcon()));
        addContent(new MetaProperty("fb:app_id", "294839814987467"));
        addContent(new Link("https://fonts.googleapis.com/css2?family=Comfortaa:wght@700&display=swap", STYLESHEET));
        addContent(new Link("https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap", STYLESHEET));
        addContent(new Link("https://fonts.googleapis.com/css2?family=Roboto&display=swap", STYLESHEET));
        addContent(new Link("css/boot2.css", STYLESHEET));
        addContent(new HtmlTag(SCRIPT).as("src", "js/index.js"));
        addContent(new Link("img/favicon.ico", "icon").as("type", "image/ico"));
        addContent(new Link("", "canonical"));
        addContent(new Link("/feed/feed.xml", "alternate").as("type", "application/atom+xml").as("title", "Projekty v Petržalke"));

    }

    private void googleAnalytics() {
        addContent(new HtmlTag(SCRIPT).as("src", "https://www.googletagmanager.com/gtag/js?id=UA-130722892-1").as("async"));
        addContent(new HtmlTag(SCRIPT).with("""
                window.dataLayer = window.dataLayer || [];
                function gtag() {
                  dataLayer.push(arguments);
                }
                gtag('js', new Date());
                gtag('config', 'UA-130722892-1');
                """));
    }
}
