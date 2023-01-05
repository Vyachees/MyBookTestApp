package com.example.unit10maven.data;


import com.example.unit10maven.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends
        CrudRepository<Book, Long>
        ,PagingAndSortingRepository<Book, Long>
        ,JpaRepository<Book, Long>
        ,JpaSpecificationExecutor<Book>
        ,BookComplexQueryRepository
        ,PriceCalcRepository


{

    List<Book> findBooksByYears(int years);

    Book findByYearsAndTitle(int years, String title);

    @Query("select aob.book from "
            + "AuthorOfBook aob join aob.book "
            + "join aob.author "
            + "where  aob.author.lastname = ?1")
    List<Book> findByAuthorLastname(String authorLastName);

}
