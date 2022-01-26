package ru.bulish.spring.geek_shop_boot.repository.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.entity.ProductSearchConditional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Specification for adding predicates in order to filter on exact params
 * @author Sorokina
 * @version 1.0
 */
@RequiredArgsConstructor
public class ProductSpecificationPredicates implements Specification<Product> {
    private final ProductSearchConditional productSearchConditional;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        addMinAndMax(root,criteriaBuilder,predicates);
        addTitle(root,criteriaBuilder, predicates);
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }


    private void addMinAndMax(Root<Product> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates){
        if (productSearchConditional.getMaxPrice() != null || productSearchConditional.getMinPrice() != null) {
            if (productSearchConditional.getMaxPrice() != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("coast"), productSearchConditional.getMaxPrice()));
            }
            if (productSearchConditional.getMinPrice() != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("coast"), productSearchConditional.getMinPrice()));
            }
            
        }
    }
    private void addTitle(Root<Product> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates){
        if (productSearchConditional.getTitle() != null) {
            predicates.add(criteriaBuilder.equal(root.get("title"), productSearchConditional.getTitle()));
        }
    }
}
