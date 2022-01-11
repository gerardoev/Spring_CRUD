package com.gerardoev.clients.Controller;

import com.gerardoev.clients.Entity.Client;
import com.gerardoev.clients.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getClientes(){
        List<Client> clients = clientService.getClients();
        if(clients.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getCliente(@PathVariable("id") long id){
        Client cliente = clientService.getClient(id);
        if(cliente == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        }
    }

    @PostMapping
    public ResponseEntity<Client> postCliente(@RequestBody Client clienteR){
        Client cliente = clientService.createClient(clienteR);
        if (cliente == null){
            return ResponseEntity.internalServerError().build();
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> putCliente(@PathVariable("id") long id, @RequestBody Client clienteR){
        Client cliente = clientService.getClient(id);
        if(cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            clienteR.setId(id);
            cliente = clientService.updateClient(clienteR);
            if (cliente ==  null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(cliente);
            }
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCliente(@PathVariable("id") long id){
        if(clientService.deleteClient(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
