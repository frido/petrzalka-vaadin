package com.example.springboot.component;

import com.example.springboot.html.*;
import com.example.springboot.page.HeadProvider;

public class Head extends HtmlTag {

    public Head(HeadProvider headProvider) {
        super("head");
        googleAnalytics();
        addContent(new HtmlTag("base").as("href", Base.resolve("/")));
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
        addContent(new Link("https://fonts.googleapis.com/css2?family=Comfortaa:wght@700&display=swap", "stylesheet"));
        addContent(new Link("https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap", "stylesheet"));
        addContent(new Link("https://fonts.googleapis.com/css2?family=Roboto&display=swap", "stylesheet"));
        addContent(new Link("css/boot2.css", "stylesheet"));
        addContent(new HtmlTag("script").as("src", "js/index.js"));
        addContent(new Link("img/favicon.ico", "icon").as("type", "image/ico"));
        addContent(new Link("", "canonical"));
        addContent(new Link("/feed/feed.xml", "alternate").as("type", "application/atom+xml").as("title", "Projekty v Petržalke"));

    }

    private void googleAnalytics() {
//        addContent(new HtmlTag("script").as("type", "text/javascript").as("src", "https://www.google-analytics.com/analytics.js").as("async"));
        addContent(new HtmlTag("script").as("src", "https://www.googletagmanager.com/gtag/js?id=UA-130722892-1").as("async"));
        addContent(new HtmlTag("script").with("\n" +
                "      window.dataLayer = window.dataLayer || [];\n" +
                "      function gtag() {\n" +
                "        dataLayer.push(arguments);\n" +
                "      }\n" +
                "      gtag('js', new Date());\n" +
                "\n" +
                "      gtag('config', 'UA-130722892-1');\n" +
                "    "));
    }
}
