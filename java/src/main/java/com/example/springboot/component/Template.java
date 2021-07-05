package com.example.springboot.component;

import com.example.springboot.Configuration;
import com.example.springboot.html.Html;
import com.example.springboot.html.HtmlTag;
import com.example.springboot.page.ConfigurationProvider;
import com.example.springboot.page.HeadProvider;
import com.example.springboot.page.PageHeaderProvider;

public class Template {

    private final HeadProvider headProvider;
    private final PageHeaderProvider pageheaderProvider;
    private final ContentProvider contentProvider;
    private final ConfigurationProvider configProvider;

    public Template(ConfigurationProvider configProvider, ContentProvider contentProvider, HeadProvider headProvider, PageHeaderProvider pageheaderProvider) {
        this.configProvider = configProvider;
        this.contentProvider = contentProvider;
        this.headProvider = headProvider;
        this.pageheaderProvider = pageheaderProvider;
    }

    @Override
    public String toString() {
        Html html = new Html("sk");
        html.addContent(new Head(configProvider, headProvider));
        HtmlTag body = new HtmlTag("body");
        body.addContent(new PageHeader(pageheaderProvider));
        contentProvider.applyContent(body);
        html.addContent(body);
        return html.toString();
    }
}
