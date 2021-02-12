package com.retail.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.model.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Integer> {

}
