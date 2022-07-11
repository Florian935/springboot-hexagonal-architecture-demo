package com.florian935.hexagonalarchitecture.infrastructure.adapter.output.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.Objects;

public abstract class BaseSpecification<E> {

    protected <V> Specification<E> equals(String field, V value) {
        return (root, query, criteriaBuilder) ->
                check(value, criteriaBuilder.equal(root.get(field), value));
    }

    protected <V extends Comparable<? super V>> Specification<E> greaterThanOrEqualTo(String field, V value) {
        return (root, query, criteriaBuilder) ->
                check(value, criteriaBuilder.greaterThanOrEqualTo(root.get(field), value));
    }

    protected <V extends Comparable<? super V>> Specification<E> lessThanOrEqualTo(String field, V value) {
        return (root, query, criteriaBuilder) ->
                check(value, criteriaBuilder.lessThanOrEqualTo(root.get(field), value));
    }

    protected Specification<E> notNull(String field) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.not(root.get(field).isNull());
    }

    protected Specification<E> notContain(String field, String value) {
        return (root, query, criteriaBuilder) ->
                check(value, criteriaBuilder.notLike(root.get(field), buildLikeExpression(value)));
    }

    protected Specification<E> notContain(String field, String value, Boolean ignoreCase) {
        return (root, query, criteriaBuilder) ->
                ignoreCase
                        ? check(
                        value,
                        criteriaBuilder.notLike(
                                criteriaBuilder.lower(root.get(field)),
                                buildLikeExpression(value.toLowerCase())
                        ))
                        : check(value, criteriaBuilder.notLike(root.get(field), buildLikeExpression(value)));

    }

    private String buildLikeExpression(String value) {
        final String LIKE_TOKEN = "%";
        return String.format("%s%s%s", LIKE_TOKEN, value, LIKE_TOKEN);
    }

    private static <V> Predicate check(V value, Predicate predicate) {
        return Objects.isNull(value) ? null : predicate;
    }
}
