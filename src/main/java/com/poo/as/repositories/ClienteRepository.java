package com.poo.as.repositories;

import com.poo.as.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Modifying
    @Query("update Cliente u set u.name = ?1, u.age = ?2  where u.id = ?3")
    void update(String name, int age, String profession, int clienteId);

    List<Cliente> findByAge(int age);
}
