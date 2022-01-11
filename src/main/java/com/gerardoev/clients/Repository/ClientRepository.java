package com.gerardoev.clients.Repository;

import com.gerardoev.clients.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
