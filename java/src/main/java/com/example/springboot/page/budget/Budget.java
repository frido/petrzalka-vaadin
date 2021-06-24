package com.example.springboot.page.budget;

import com.example.springboot.page.project.Program;
import com.example.springboot.page.project.ProgramAttributeConverter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    private int id;
    private String title;
    private int year;
    @Convert(converter = ProgramAttributeConverter.class)
    private Program program;
    @Column(name = "amount_original")
    private BigDecimal amountOriginal;
    @Column(name = "amount_updated")
    private BigDecimal amountUpdated;
    @Column(name = "amount_real")
    private BigDecimal amountReal;
    private String comment;
    @Enumerated(EnumType.STRING)
    private BudgetStatus status;
    // TODO: previous column

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public BigDecimal getAmountOriginal() {
        return amountOriginal;
    }

    public void setAmountOriginal(BigDecimal amountOriginal) {
        this.amountOriginal = amountOriginal;
    }

    public BigDecimal getAmountUpdated() {
        return amountUpdated;
    }

    public void setAmountUpdated(BigDecimal amountUpdated) {
        this.amountUpdated = amountUpdated;
    }

    public BigDecimal getAmountReal() {
        return amountReal;
    }

    public void setAmountReal(BigDecimal amountReal) {
        this.amountReal = amountReal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BudgetStatus getStatus() {
        return status;
    }

    public void setStatus(BudgetStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", program=" + program +
                ", amountOriginal=" + amountOriginal +
                ", amountUpdated=" + amountUpdated +
                ", amountReal=" + amountReal +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                '}';
    }
}
