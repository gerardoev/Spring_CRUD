package com.gerardoev.clients.Service;

import com.gerardoev.clients.Entity.Client;

import java.util.List;

public interface ClientServiceI {
    public Client createClient(Client client);
    public Client getClient(long id);
    public Client updateClient(Long id, Client client);
    public boolean deleteClient(long id);
    public List<Client> getClients();
}
