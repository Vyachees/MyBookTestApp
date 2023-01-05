package com.example.unit10maven.data;

import com.example.unit10maven.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BooksSpecification {

    public static Specification<Book> yearsLessThan(int years) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("years"), years);
    }

    public static Specification<Book> yearGreaterThan(int years) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("years"), years);
    }

    public static Specification<Book> yearsInRange(int startYears, int finishYears) {

        return yearsLessThan(finishYears).and(yearGreaterThan(startYears));

    }
}
