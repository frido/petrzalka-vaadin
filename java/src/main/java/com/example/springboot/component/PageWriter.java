package com.example.springboot.component;

import com.example.springboot.Configuration;
import com.example.springboot.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
