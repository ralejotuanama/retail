package com.retail.service;

import java.util.List;

import com.retail.model.Cliente;


public interface IClienteService {

	Cliente registrar(Cliente obj) throws Exception;

	Cliente modificar(Cliente obj) throws Exception;

	List<Cliente> listar() throws Exception;

	Cliente listarPorId(Integer id) throws Exception;

	void eliminar(Integer id) throws Exception;

}
