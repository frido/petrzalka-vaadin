package com.example.springboot.component;

import com.example.springboot.html.*;
import com.example.springboot.page.PageHeaderProvider;

public class PageHeader extends Header {

    public static final String POSTS = "/posts/";
    public static final String GRANTS = "/grants/";
    public static final String BUDGET = "/investicny_plan/";

    public PageHeader(PageHeaderProvider pageheaderProvider) {
        super("row paletteA px-md-4 border-bottom shadow-sm logo");
    }

    @Override
    public String toString() {
        addContent(new Div("col-md-1", new Img("img/logo-m.png", "")));
        Div mainDiv = new Div("col-md-11");
        addContent(mainDiv);

        Div row = new Div("row");
        AHref a = new AHref("", "/", new H(1, "", new HtmlNoTag("Projekty v Petržalke")));
        row.addContent(a);

        HtmlTag nav = new HtmlTag("nav");
        nav.addAttr("class", "row");
        addNavigationLink(nav, "/", "Home");
        addNavigationLink(nav, POSTS, "Projekty");
        addNavigationLink(nav, GRANTS, "Granty");
        addNavigationLink(nav, BUDGET, "Inv. Plán");
//        addNavigationLink(nav, "/ihriska/", "Ihriská");
        row.addContent(nav);

        mainDiv.addContent(row);
        return super.toString();
    }

    private void addNavigationLink(HtmlTag nav, String href, String name) {
        nav.addContent(new Div("col-md-2 col-sm-4 col-6", new AHref(" p-2 nav-item", href, new HtmlNoTag(name))));
    }
}
