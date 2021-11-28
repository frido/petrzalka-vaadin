package com.example.application.petrzalka.component;

import com.example.application.petrzalka.html.AHref;
import com.example.application.petrzalka.html.HtmlTag;

public class Figure extends HtmlTag {

    public Figure(String source, String title) {
        super("figure");
        AHref a = new AHref("", source, new HtmlTag("img").as("src", source));
        if (title != null) {
        	a.with(title);
        }
        addContent(a);
    }

}
