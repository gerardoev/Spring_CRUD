package com.gerardoev.clients.Repository;

import com.gerardoev.clients.Entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
