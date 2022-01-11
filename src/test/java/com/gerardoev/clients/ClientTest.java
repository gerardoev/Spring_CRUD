package com.gerardoev.clients;

import com.gerardoev.clients.Entity.Client;
import com.gerardoev.clients.Repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClientTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testInsertion(){
        Client client1 = Client.builder()
                .correo("gerardoerick@utlook.com")
                .nombre("Gerardo Erick")
                .build();
        clientRepository.save(client1);

        List<Client> clients = clientRepository.findAll();
        Assertions.assertEquals(1, clients.size());
    }
}
