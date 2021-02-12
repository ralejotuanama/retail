package com.retail.controller;

import java.net.URI;
import java.text.DecimalFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.retail.model.Cliente;
import com.retail.service.IClienteService;

@RestController
@RequestMapping()
public class ClienteController {

	@Autowired
	private IClienteService service;

	@GetMapping("/listclientes")
	public ResponseEntity<List<Cliente>> listar() throws Exception {
		List<Cliente> lista = service.listar();
		// int total = lista.stream().mapToInt(Cliente::getEdad).sum();
		return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Cliente obj = service.listarPorId(id);
		
		return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
	}

	@GetMapping("/kpideclientespromedio")
	public String Promedio() throws Exception {
		List<Cliente> lista = service.listar();
		Double promedio = lista.stream().mapToInt(Cliente::getEdad).average().getAsDouble();
		return String.valueOf(promedio);
	}

	@GetMapping("/kpideclientesdesviacion")
	public String Promedio2() throws Exception {
		List<Cliente> lista = service.listar();

		double media = 0.0;
		double sumatoria = 0.0;
		double varianza = 0.0;
		double v2 = 0.0;
		double v3 = 0.0;
		

		for (Cliente cliente : lista) {

			sumatoria += cliente.getEdad();

		}

		media = sumatoria / (lista.size());

		for (Cliente cliente : lista) {

			double v1 = (double) cliente.getEdad() - media;

			v2 += Math.pow(v1, 2);

		}

		v3 = lista.size() - 1;

		varianza = v2 / v3;
		
		DecimalFormat df = new DecimalFormat("#.##");

		return String.valueOf(df.format(Math.sqrt(varianza)));
		
		
	}

	@PostMapping("/creacliente")
	public ResponseEntity<Void> registrar(@Valid @RequestBody Cliente cliente) throws Exception {
		Cliente obj = service.registrar(cliente);

		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCliente())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
