package com.poo.as.services;

import com.poo.as.dtos.ClienteResponseDTO;
import com.poo.as.entities.Cliente;
import com.poo.as.exceptions.NotFoundCliente;
import com.poo.as.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    ClienteRepository repository;
    @Autowired
    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    public List<ClienteResponseDTO> getAll(){
        List<Cliente> clienteResult = this.repository.findAll();
        if(clienteResult.isEmpty()) throw new NotFoundCliente();
        List<ClienteResponseDTO> listClienteDto = new ArrayList<>();
        for(int i = 0; i < clienteResult.size();i++){
            ClienteResponseDTO clienteDto = new ClienteResponseDTO(clienteResult.get(i).getName(),clienteResult.get(i).getAge(),clienteResult.get(i).getProfession());
            listClienteDto.add(clienteDto);
        }
        return listClienteDto;
    }

    public Optional<Cliente> getById(int clienteId){
        Optional<Cliente> clienteResult = this.repository.findById(clienteId);
        if(clienteResult.isEmpty()) throw new NotFoundCliente();
        return clienteResult;
    }

    public List<Cliente> getByAge(int age) {
        List<Cliente> clientesByAge = this.repository.findByAge(age);
        if(clientesByAge.isEmpty()) {
            throw new NotFoundCliente();
        }
        return clientesByAge;
    }
    public ClienteResponseDTO createCliente(ClienteResponseDTO cliente) {
        //Convertendo DTO -> Entity
        Cliente clienteResponseDtoToCliente = new Cliente(cliente.getName(), cliente.getAge(), cliente.getProfession());
        Cliente resultadoSave = this.repository.save(clienteResponseDtoToCliente);
        //Convertendo Resultado Entity para DTO
        return new ClienteResponseDTO(resultadoSave.getName(), resultadoSave.getAge(), resultadoSave.getProfession());
    }


    @Transactional
    public String update(Cliente clienteData, int clienteId) {
        Optional<Cliente> optionalCliente = this.repository.findById(clienteId);
        if (optionalCliente.isEmpty()) {
            throw new NotFoundCliente();
        }

        Cliente clienteToUpdate = optionalCliente.get();
        clienteToUpdate.setName(clienteData.getName());
        clienteToUpdate.setAge(clienteData.getAge());
        clienteToUpdate.setProfession(clienteData.getProfession());

        Cliente updateCliente = this.repository.save(clienteToUpdate);

        return "Cliente Atualizado: " + updateCliente;
    }
    public String delete(int clienteId){
        Optional<Cliente> cliente = this.repository.findById(clienteId);
        if (cliente.isEmpty()) throw new NotFoundCliente();
        this.repository.deleteById(clienteId);
        return "Cliente Removido com Sucesso!" + cliente;
    }
}

