package com.gerardoev.clients.Service;

import com.gerardoev.clients.Entity.Client;
import com.gerardoev.clients.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService implements ClientServiceI{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClient(long id) {
        Client findResponse = clientRepository.findById(id);
        return findResponse;
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client findResponse = clientRepository.findById(id);
        if( findResponse == null){
            return null;
        }
        Client clientDB = findResponse;
        clientDB.setNombre(client.getNombre());
        clientDB.setCorreo(client.getCorreo());
        return clientRepository.save(clientDB);
    }

    @Override
    public boolean deleteClient(long id) {
        clientRepository.deleteById(id);
        Client findResponse = clientRepository.findById(id);
        if( findResponse == null){
            return true;
        }
        return false;
    }

    @Override
    public List<Client> getClients() {
        ArrayList<Client> found = (ArrayList<Client>) clientRepository.findAll();

        return found;
    }
}
