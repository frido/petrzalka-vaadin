package com.example.application.petrzalka.page.grant;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.application.petrzalka.component.Amount;
import com.example.application.petrzalka.html.*;

public class ArticleSportListComponent extends HtmlTag {
    private Collection<GrantDto> grantItems;

    public ArticleSportListComponent(Collection<GrantDto> grantItems) {
        super("article");
        this.grantItems = grantItems;
    }

    @Override
    public String toString() {
        Div row = new Div("row");
        addContent(row);

        List<GrantDto> sortedList = grantItems.stream()
                .sorted((a, b) -> b.getCurrentAmount().compareTo(a.getCurrentAmount()))
                .collect(Collectors.toList());

        for(GrantDto grantItem : sortedList) {
            row.addContent(grant(grantItem));
        }

        return super.toString();
    }

    private HtmlTag grant(GrantDto grantItem) {
        return new Div("grant col-lg-3 col-md-6 col-sm-6")
                .with(article(grantItem));
    }

    private HtmlTag article(GrantDto grant) {
        HtmlTag article = new HtmlTag("article").clazz("box h-100 inwork text-center")
                .with(new H(3, "", grant.getTitle()))
                .with(new HtmlTag("p").with(new Span("amount", Amount.of(grant.getCurrentAmount()).toString())));
        if (grant.getCurrentDetail() != null) {
            article.with(new HtmlTag("p").with(grant.getCurrentDetail()));
        }
        for(GrantItem older : grant.getOldGrants()) {
            article.with(new HtmlTag("p"))
                    .with(new Div("", new Small(getOldDescription(older))));
        }
        return article;
    }

    private String getOldDescription(GrantItem grantItem) {
        return Amount.of(grantItem.getAmount()) + " (rok " + grantItem.getYear() + ")";
    }

}
