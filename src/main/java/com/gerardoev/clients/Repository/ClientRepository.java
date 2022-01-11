package com.gerardoev.clients.Repository;

import com.gerardoev.clients.Entity.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientRepository implements ClientRepositoryI {
    Gson gson = new GsonBuilder().create();

    @Override
    public Client save(Client client) {
        ArrayList<Client> clients = (ArrayList<Client>) findAll();
        try {
            Writer fw = new FileWriter("db.json");
            clients.add(client);
            gson.toJson(clients, fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        try {
            Reader rd = new FileReader("db.json");
            ArrayList<Client> clients = gson.fromJson(rd, ArrayList.class);
            rd.close();
            return clients;
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Client findById(long id) {
        return null;
    }
}
