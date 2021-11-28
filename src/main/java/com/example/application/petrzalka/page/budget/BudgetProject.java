package com.example.application.petrzalka.page.budget;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "budget_project")
public class BudgetProject {
  @Id
  private int id;
  private String title;
  private String description;
  private String url;
  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @OrderBy("year DESC")
  private Set<Budget> budgets = new HashSet<>(0);

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Set<Budget> getBudgets() {
    return budgets;
  }

  public void setBudgets(Set<Budget> budgets) {
    this.budgets = budgets;
  }

  @Transient
  public BigDecimal getAmount(int year) {
    return budgets.stream().filter(p -> p.getYear() == year).map(Budget::getAmount).reduce(BigDecimal.ZERO,
        BigDecimal::add);
  }

  @Transient
  public boolean isAnyInWork(int year) {
    return budgets.stream()
      .filter(p -> p.getYear() == year)
      .anyMatch(x -> x.getStatus().equals(BudgetStatus.INWORK));
  }

  @Transient
  public BigDecimal getAmountAll() {
    return budgets.stream().map(Budget::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  @Transient
  public List<Integer> getYears() {
    return budgets.stream().map(Budget::getYear).distinct().sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
  }

  @Transient
  public List<Budget> getBudgets(Integer year) {
    return budgets.stream().filter(b -> b.getYear() == year).sorted(Comparator.comparing(Budget::getAmount).reversed())
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return getTitle();
  }

}
