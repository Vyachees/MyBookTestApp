package com.example.unit10maven.data;


import com.example.unit10maven.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookComplexQueryRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> complexQueryMethod() {
        return jdbcTemplate.query(
                "select id, title, description, years from book",
                (rs, rowNum) -> Book.builder()
                        .id(rs.getLong("id"))
                        .years(rs.getInt("years"))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .build()
        );
    }
}
