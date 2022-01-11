package com.gerardoev.clients.Repository;

import com.gerardoev.clients.Entity.Client;

import java.util.List;

public interface ClientRepositoryI {
    public Client save(Client client);
    public List<Client> findAll();
    public void deleteById(long id);
    public Client findById(long id);
}
