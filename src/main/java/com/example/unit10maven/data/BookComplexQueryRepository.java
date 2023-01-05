package com.example.unit10maven.data;



import com.example.unit10maven.entity.Book;

import java.util.List;

public interface BookComplexQueryRepository {
    List<Book> complexQueryMethod();
}
