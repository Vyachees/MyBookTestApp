package com.example.unit10maven.data;

import com.example.unit10maven.entity.PurchasedBook;
import org.springframework.data.repository.CrudRepository;



public interface PurchasedBookRepository  extends CrudRepository<PurchasedBook, Long> {

}
