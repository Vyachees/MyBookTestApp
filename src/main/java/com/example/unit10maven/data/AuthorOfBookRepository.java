package com.example.unit10maven.data;


import com.example.unit10maven.entity.AuthorOfBook;
import org.springframework.data.repository.CrudRepository;

public interface AuthorOfBookRepository extends CrudRepository<AuthorOfBook, Long> {
}
