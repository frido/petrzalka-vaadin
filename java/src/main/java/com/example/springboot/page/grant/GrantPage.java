package com.example.springboot.page.grant;

import com.example.springboot.component.PageHeader;
import com.example.springboot.component.Title;
import com.example.springboot.html.HtmlTag;
import com.example.springboot.html.Section;
import com.example.springboot.page.BasePage;

import java.util.stream.Stream;

public class GrantPage extends BasePage {

    private final GrantService grantService;

    public GrantPage(GrantService grantService) {
        super();
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
