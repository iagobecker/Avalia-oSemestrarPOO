package com.poo.as.controllers;

import com.poo.as.dtos.ClienteResponseDTO;
import com.poo.as.entities.Cliente;
import com.poo.as.exceptions.NotFoundCliente;
import com.poo.as.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
     ClienteService service;
    @Autowired
    public ClienteController(ClienteService service){
        this.service = service;
    }

    @GetMapping()
    public List<ClienteResponseDTO> getAll(){
        return this.service.getAll();
    }
    @GetMapping("/{id}")
    public ClienteResponseDTO getById(@PathVariable int id){
        Optional<Cliente> clienteOptional = this.service.getById(id);
        if (clienteOptional.isEmpty()) {
            throw new NotFoundCliente();
        }
        Cliente cliente = clienteOptional.get();
        return new ClienteResponseDTO(
                cliente.getName(),
                cliente.getAge(),
                cliente.getProfession()
        );
    }
    @GetMapping(params = "idade")
    public List<ClienteResponseDTO> getByAge(@RequestParam int idade) {
        List<Cliente> clientesByAge = this.service.getByAge(idade);
        List<ClienteResponseDTO> responseDTOs = new ArrayList<>();
        for (Cliente cliente : clientesByAge) {
            responseDTOs.add(new ClienteResponseDTO(
                    cliente.getName(),
                    cliente.getAge(),
                    cliente.getProfession()
            ));
        }
        return responseDTOs;
    }
    @PostMapping
    public ClienteResponseDTO createCliente(@RequestBody ClienteResponseDTO bodyRequest){
        return this.service.createCliente(bodyRequest);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Cliente clienteData) {
        return this.service.update(clienteData, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        return this.service.delete(id);
    }

}
