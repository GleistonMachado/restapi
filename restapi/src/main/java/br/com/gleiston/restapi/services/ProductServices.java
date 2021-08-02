package br.com.gleiston.restapi.services;

import java.util.List;

import br.com.gleiston.restapi.model.Product;

public interface ProductServices {
	public List<Product> findAll();
	public Product find(Long id);
	public Product create(Product product);
	public Product update(Long id, Product product);
	public void delete(Long id);
	
}
