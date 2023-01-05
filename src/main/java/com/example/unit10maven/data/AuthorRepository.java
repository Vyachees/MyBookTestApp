package com.example.unit10maven.data;


import com.example.unit10maven.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
