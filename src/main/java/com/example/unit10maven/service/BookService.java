package com.example.unit10maven.service;


import com.example.unit10maven.entity.Book;
import com.example.unit10maven.entity.Client;
import com.example.unit10maven.entity.PurchasedBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookService {

    void save(Book book);

    Optional<Book> findById(Long id);

    Page<Book> findAtPage(int pageIndex, int rowCount, Sort.Direction direction, String field);

    List<Book> findSame(Book book);

    List<Book> findByYears(int years);

    List<Book> findByAuthor(Long author_id);

    List<Book> complexQuery();

    BigDecimal getSumPriceByBook(Book book);

    BigDecimal getSumPriceByClient(Client client);

   // void purchaseBook(Client client, Book book, BigDecimal price);


}
