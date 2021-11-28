package com.example.application.petrzalka.page.budget;

import javax.persistence.*;

import com.example.application.petrzalka.page.project.Program;
import com.example.application.petrzalka.page.project.ProgramAttributeConverter;

import java.math.BigDecimal;

@Entity
@Table(name = "budget")
public class Budget implements Comboboxable {
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
    @ManyToOne()
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private BudgetProject project;
    @Column(name = "project_id", insertable = false, updatable = false)
    private Integer projectId;
    @Column(name = "use_amount_real")
    private Boolean useAmountReal;
    @Column(name = "show_comment")
    private Boolean showComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
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

    public BudgetProject getProject() {
        return project;
    }

    public void setProject(BudgetProject project) {
        this.project = project;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public BigDecimal getAmount() {
        return useAmountReal ? amountReal : amountOriginal;
    }

    public Boolean getUseAmountReal() {
        return useAmountReal;
    }

    public void setUseAmountReal(Boolean useAmountReal) {
        this.useAmountReal = useAmountReal;
    }

    public Boolean getShowComment() {
        return showComment;
    }

    public void setShowComment(Boolean showComment) {
        this.showComment = showComment;
    }
}
