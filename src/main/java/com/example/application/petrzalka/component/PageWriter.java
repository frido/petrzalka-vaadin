package com.example.application.petrzalka.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.application.Configuration;
import com.example.application.petrzalka.page.Page;

public class PageWriter {
    private final Configuration configuration;

    public PageWriter(Configuration configuration) {
        this.configuration = configuration;
    }

    public void write(Page page) {
        Path fileName = Path.of(configuration.getOutputDir(), page.getFileName() + ".html");
        try {
            System.out.println(fileName.toAbsolutePath());
            Files.createDirectories(fileName.getParent());
            Files.writeString(fileName, page.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
