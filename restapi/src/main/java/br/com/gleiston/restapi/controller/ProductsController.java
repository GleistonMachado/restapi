package br.com.gleiston.restapi.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gleiston.restapi.model.Product;
import br.com.gleiston.restapi.services.ProductServices;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductServices ps;
	
	public ProductsController(ProductServices ps) {
		this.ps = ps;
	}

	@GetMapping
	@ResponseBody                       // Quando quero retorna algo
	public ResponseEntity<?> findAll() {
		List<Product> list = this.ps.findAll();
		return new ResponseEntity<List>(list, HttpStatus.OK);  // HttpStatus.OK = 200
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> find(@PathVariable(value = "id") Long id) {
		Product product = this.ps.find(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
		
		if(!errors.hasErrors()) {
			Product productCreted = this.ps.create(product);
			return new ResponseEntity<Product>(productCreted, HttpStatus.CREATED);	// HttpStatus.CREATED = code 201
		}
		
		return ResponseEntity
				.badRequest()
				.body(errors
						.getAllErrors()
						.stream()
						.map(msg -> msg.getDefaultMessage())
						.collect(Collectors.joining(",")));
		
		
	}
	
	@PutMapping(value = "/{id}")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Product product, Errors errors) {
		
		if(!errors.hasErrors()) {
			Product productUpdate = this.ps.update(id, product);
			return new ResponseEntity<Product>(productUpdate, HttpStatus.OK);
		}
		
		return ResponseEntity
				.badRequest()
				.body(errors
						.getAllErrors()
						.stream()
						.map(msg -> msg.getDefaultMessage())
						.collect(Collectors.joining(",")));
		
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(value = "id") Long id, HttpServletResponse response) {
		this.ps.delete(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}
