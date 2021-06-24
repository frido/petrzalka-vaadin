package com.example.springboot.page.project;

public enum ProjectPhase {
    UZEMNE_KONANIE("Územné konanie"),
    PRIPRAVA_PROJEKTU("Príprava projektu"),
    ARCHITEKTONICKA_SUTAZ("Architektonická súťaž"),
    INVESTICNY_ZAMER("Investičný zámer"),
    URBANISTICKY_NAVRH("Urbanistický návrh"),
    PROJEKTOVA_DOKUMENTACIA("Projektová dokumentácia"),
    REALIZACIA("Realizacia");

    private final String label;

    ProjectPhase(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
