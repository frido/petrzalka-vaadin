package com.example.application.old;

public class Configuration {

    private String outputDir = "_site";
    private String base = "";

    public String getOutputDir() {
        return outputDir;
    }

    public String getBase() {
        return base;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
