package com.example.application.petrzalka.page.grant;

import java.util.stream.Stream;

import com.example.application.Configuration;
import com.example.application.petrzalka.component.PageHeader;
import com.example.application.petrzalka.component.Title;
import com.example.application.petrzalka.html.HtmlTag;
import com.example.application.petrzalka.html.Section;
import com.example.application.petrzalka.page.BasePage;

public class GrantPage extends BasePage {

    private final GrantService grantService;

    public GrantPage(Configuration conf, GrantService grantService) {
        super(conf);
        this.grantService = grantService;
    }

    public void applyContent(HtmlTag body) {
        Stream.of(GrantCategory.SPORT, GrantCategory.OZ)
                .map(this::grant).forEach(body::addContent);
    }

    private HtmlTag grant(GrantCategory grantCategory) {
        return new Section()
                .with(new Title(grantCategory.getLabel()))
                .with(new ArticleSportListComponent(grantService.getGrantTreeByCategory(grantCategory, 99)));
    }

    @Override
    public String getFileName() {
        return PageHeader.GRANTS + "index";
    }
}
