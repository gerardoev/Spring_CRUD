package com.gerardoev.clients.Service;

import com.gerardoev.clients.Entity.Client;
import com.gerardoev.clients.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService implements ClientServiceI{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClient(long id) {
        Optional<Client> findResponse = clientRepository.findById(id);
        if(findResponse.isEmpty()){
            return null;
        }else {
            return findResponse.get();
        }
    }

    @Override
    public Client updateClient(Client client) {
        Optional<Client> findResponse = clientRepository.findById(client.getId());
        if( findResponse.isEmpty()){
            return null;
        }
        Client clientDB = findResponse.get();
        clientDB.setNombre(client.getNombre());
        clientDB.setCorreo(client.getCorreo());
        return clientRepository.save(clientDB);
    }

    @Override
    public boolean deleteClient(long id) {
        clientRepository.deleteById(id);
        Optional<Client> findResponse = clientRepository.findById(id);
        if( findResponse.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public List<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>();
        Iterable<Client> foundIterable = clientRepository.findAll();
        for (Client client : foundIterable) {
            clients.add(client);
        }
        return clients;
    }
}
