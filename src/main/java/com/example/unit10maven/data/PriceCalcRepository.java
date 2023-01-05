package com.example.unit10maven.data;

import com.example.unit10maven.entity.Book;
import com.example.unit10maven.entity.Client;
import com.example.unit10maven.entity.PurchasedBook;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface PriceCalcRepository {


    //@Query("SELECT SUM(m.totalDays) FROM MyEntity m")
    //@Query("SELECT SUM(total_days) FROM MyEntity")
    @Query("select sum(pb.price) " +
            "from PurchasedBook pb " +
            "where pb.book=?1"
    )
    BigDecimal getSumPriceByBook(Book book);

    @Query("select sum(pb.price) " +
            "from PurchasedBook pb " +
            "where pb.client=?1")
    BigDecimal getSumPriceByClient(Client client);
}
