package com.gerardoev.clients.Repository;

import com.gerardoev.clients.Entity.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

@Component
public class ClientRepository implements ClientRepositoryI {
    Gson gson = new GsonBuilder().create();

    @Override
    public Client save(Client client) {


        try {
            Reader rd = new FileReader("db.json");
            Type type = new TypeToken<Map<Long, Client>>(){}.getType();
            HashMap<Long, Client> clients =  gson.fromJson(rd, type);
            rd.close();
            if(clients == null)
                clients = new HashMap<>();
            Writer fw = new FileWriter("db.json");
            Long id = 1L;
            client.setId(id);
            clients.put(id,client);
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
            ArrayList<Client> clients = new ArrayList();
            Reader rd = new FileReader("db.json");
            Type type = new TypeToken<Map<Long, Client>>(){}.getType();
            HashMap<Long, Client> clientsDB =  gson.fromJson(rd, type);
            for ( Client client : clientsDB.values()) {
                clients.add(client);
            }
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
        HashMap<Long, Client> clients = getDB();
        clients.remove(id);
        try {
            Writer fw = new FileWriter("db.json");
            gson.toJson(clients, fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client findById(long id) {
        HashMap<Long, Client> clients = getDB();
        if(clients == null){
            return null;
        }else{
            for ( Client client : clients.values()) {
                if(client.getId().equals(id)){
                    return client;
                }
            }
        }
        return null;
    }

    private HashMap<Long, Client> getDB(){
        try {
            Reader rd = new FileReader("db.json");
            Type type = new TypeToken<Map<Long, Client>>(){}.getType();
            HashMap<Long, Client> clientsDB =  gson.fromJson(rd, type);
            rd.close();
            return clientsDB;
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }

}
