package com.example.unit10maven.service;


import com.example.unit10maven.data.BookRepository;
import com.example.unit10maven.entity.Book;
import com.example.unit10maven.entity.Client;
import com.example.unit10maven.entity.PurchasedBook;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        this.bookRepository.save(book);

    }

    @Override
    public Optional<Book> findById(Long id) {

        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> findAtPage(int pageIndex, int rowCount,
                                 Sort.Direction direction, String field) {
        PageRequest pageRequest =  PageRequest.of(pageIndex, rowCount,
                direction, field);
        return bookRepository.findAll(pageRequest);

    }

    @Override
    public List<Book> findSame(Book book) {
        return bookRepository.findAll(Example.of(book));
    }

    @Override
    public List<Book> findByYears(int years) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(Long author_id) {
        return null;
    }

    @Override
    public List<Book> complexQuery() {
        return null;
    }

    @Override
    public BigDecimal getSumPriceByBook(Book book) {
        return bookRepository.getSumPriceByBook(book);
    }

    @Override
    public BigDecimal getSumPriceByClient(Client client) {
        return bookRepository.getSumPriceByClient(client);
    }


    //@Override
   // public void purchaseBook(Client client, Book book, BigDecimal price) {

   // }
}
