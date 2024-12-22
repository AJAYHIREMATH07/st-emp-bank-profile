package com.bankX.bank.Specification;

import com.bankX.bank.Filter.CustomerFilters;
import com.bankX.bank.entity.CustomerEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecifications implements Specification<CustomerEntity>{

    private final CustomerFilters customerFilters;

    public CustomerSpecifications(CustomerFilters customerFilters) {
        this.customerFilters = customerFilters;
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // Start building the Predicate (filtering conditions)
        Predicate predicate = criteriaBuilder.conjunction();  // Start with a "true" condition

        if (customerFilters.getName() != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + customerFilters.getName().toLowerCase() + "%"));
        }

        if (customerFilters.getId() != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get("id"), customerFilters.getId()));
        }
        return predicate;
    }
}
