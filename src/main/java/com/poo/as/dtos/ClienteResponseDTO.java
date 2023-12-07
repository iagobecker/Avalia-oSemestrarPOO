package com.poo.as.dtos;

import com.poo.as.entities.Cliente;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ClienteResponseDTO {
    public ClienteResponseDTO(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
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
}
