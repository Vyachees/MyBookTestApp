package com.example.unit10maven.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Client {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

}
