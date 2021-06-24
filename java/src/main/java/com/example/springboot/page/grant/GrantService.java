package com.example.springboot.page.grant;

import com.example.springboot.criteria.*;
import com.example.springboot.model.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GrantService {

    @PersistenceContext
    private EntityManager em;
    private final InterfaceCriteriaBuilder<GrantItem> defaultOrder = new AttributeOrderCriteriaBuilder<>(List.of(GrantItem_.year, GrantItem_.amount));

    public Collection<GrantDto> getGrantTreeByCategory(GrantCategory category, int limit) {
        List<GrantItem> grantList = this.findByCriteria(
                new SubqueryInCriteriaBuilder<>(GrantItem_.subjectId, GrantSubject_.id,
                    new EqualsCriteriaBuilder<>(GrantSubject_.category, category)), limit);

        Map<GrantSubject, GrantDto> dtoList = new HashMap<>();
        grantList.forEach(x -> {
            dtoList.put(
                    x.getSubject(),
                    dtoList.getOrDefault(x.getSubject(), new GrantDto(x.getSubject())).withGrant(x)
            );
        });
        return dtoList.values();
    }

    private List<GrantItem> findByCriteria(InterfaceCriteriaBuilder<GrantItem> criteriaBuilder, int limit) {
        return new CriteriaQueryContext(em, GrantItem.class).apply(criteriaBuilder).apply(defaultOrder).getResultList(limit);
    }
}
