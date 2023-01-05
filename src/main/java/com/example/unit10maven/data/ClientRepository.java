package com.example.unit10maven.data;

import com.example.unit10maven.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository  extends CrudRepository<Client, Long> {

    Client findByName(String name);

}
