package com.poo.as.entities;

import com.poo.as.dtos.ClienteResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public Cliente(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @NotNull(message = "Nome obrigatório")
    private String name;


    private int age;

    private String profession;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;



    //public String toString() {
       //return new ClienteResponseDTO(this.name, this.age, this.profession, new ClienteResponseDTO(this.cliente.getName(), this.cliente.getAge(), this.cliente.getProfession())).toString();
    //}
}
