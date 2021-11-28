package com.example.application.petrzalka.page.budget;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.example.application.petrzalka.criteria.AttributeOrderCriteriaBuilder;
import com.example.application.petrzalka.criteria.CriteriaQueryContext;
import com.example.application.petrzalka.criteria.EqualsCriteriaBuilder;
import com.example.application.petrzalka.criteria.InterfaceCriteriaBuilder;
import com.example.application.petrzalka.criteria.SubqueryInCriteriaBuilder;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BudgetService {

  @PersistenceContext
  private EntityManager em;
  private final InterfaceCriteriaBuilder<Budget> defaultOrder = new AttributeOrderCriteriaBuilder<>(
      Budget_.amountOriginal);
  private final InterfaceCriteriaBuilder<BudgetProject> defaultOrder2 = new AttributeOrderCriteriaBuilder<>(
      BudgetProject_.title);

  public BudgetByYearDto getBudgetForIndex() {
    List<Budget> budgetList = findByCriteria(new EqualsCriteriaBuilder<>(Budget_.year, 2021), 4);
    return new BudgetByYearDto(2021, budgetList);
  }

  public BudgetByYearDto getBudgetByYear(int year) {
    List<Budget> budgetList = findByCriteria(new EqualsCriteriaBuilder<>(Budget_.year, year), 1000);
    return new BudgetByYearDto(year, budgetList);
  }

  public List<BudgetProject> getBudgetProject(int year) {
    List<BudgetProject> budgetProjects = this.findByCriteria2(new SubqueryInCriteriaBuilder<>(BudgetProject_.id,
        Budget_.projectId, new EqualsCriteriaBuilder<>(Budget_.year, year)), 1000);
    return budgetProjects;
  }

  public List<BudgetProject> getAllBudgetProjects() {
    return findAll(BudgetProject.class);
  }

  public List<BudgetDto> findAllBudgeets() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    return em.createQuery("Select b from Budget b", Budget.class).getResultList().stream()
        .map(e -> modelMapper.map(e, BudgetDto.class)).collect(Collectors.toList());
  }

  public <T> List<T> findAll(Class<T> clazz) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(clazz);
    Root<T> rootEntry = cq.from(clazz);
    CriteriaQuery<T> all = cq.select(rootEntry);

    TypedQuery<T> allQuery = em.createQuery(all);
    return allQuery.getResultList();
  }

  private List<Budget> findByCriteria(InterfaceCriteriaBuilder<Budget> criteriaBuilder, int limit) {
    return new CriteriaQueryContext<>(em, Budget.class).apply(criteriaBuilder).apply(defaultOrder).getResultList(limit);
  }

  private List<BudgetProject> findByCriteria2(InterfaceCriteriaBuilder<BudgetProject> criteriaBuilder, int limit) {
    return new CriteriaQueryContext<>(em, BudgetProject.class).apply(criteriaBuilder).apply(defaultOrder2)
        .getResultList(limit);
  }

  public Optional<BudgetDto> get(Integer id) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    return Optional.of(em.find(Budget.class, id)).map(e -> modelMapper.map(e, BudgetDto.class));
  }

  public Optional<Budget> get2(Integer id) {
    return Optional.ofNullable(em.find(Budget.class, id));
  }

  @Transactional // TODO naco, preco?
  public void update(BudgetDto budget) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    Budget entity = modelMapper.map(budget, Budget.class);
    System.out.println("3");
    System.out.println(entity);
    em.merge(entity); // TODO: version, optimistic lock
    em.flush();
  }

  @Transactional // TODO naco, preco?
  public void update2(Budget entity) {
    em.merge(entity); // TODO: version, optimistic lock
    em.flush();
  }
}
