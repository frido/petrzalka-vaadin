package com.example.springboot.page.grant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GrantDto {
    private GrantSubject subject;
    private List<GrantItem> grantItems;

    public GrantDto(GrantSubject subject) {
        this.subject = subject;
        this.grantItems = new ArrayList<>();
    }

    public void addGrant(GrantItem g) {
        grantItems.add(g);
    }

    public String getTitle() {
        return subject.getTitle();
    }

    public Optional<GrantItem> getCurrentGrant() {
        return grantItems.stream().filter(GrantItem::isCurrent).findFirst();
    }

    public List<GrantItem> getOldGrants() {
        return grantItems.stream().filter(GrantItem::isOld).collect(Collectors.toList());
    }

    public BigDecimal getCurrentAmount() {
        return getCurrentGrant().map(GrantItem::getAmount).orElse(BigDecimal.ZERO);
    }

    public String getCurrentDetail() {
        return getCurrentGrant().map(GrantItem::getDetail).orElse(null);
    }

    public GrantDto withGrant(GrantItem g) {
        addGrant(g);
        return this;
    }

    @Override
    public String toString() {
        return "GrantDto{" +
                "subject=" + subject +
                ", grantItems=" + grantItems +
                '}';
    }
}
