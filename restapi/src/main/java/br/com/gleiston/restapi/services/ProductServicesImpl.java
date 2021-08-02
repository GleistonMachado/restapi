package br.com.gleiston.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleiston.restapi.model.Product;
import br.com.gleiston.restapi.repository.ProductRepository;

@Service
public class ProductServicesImpl implements ProductServices{

	@Autowired
	private ProductRepository pr;
	
	public ProductServicesImpl(ProductRepository pr) {
		this.pr = pr;
	}
	
	@Override
	public List<Product> findAll() {
		return pr.findAll();
	}

	@Override
	public Product find(Long id) {
		return this.pr.findById(id);
	}

	@Override
	public Product create(Product product) {
		this.pr.save(product);
		return product;
	}

	@Override
	public Product update(Long id, Product product) {
		Product productExists = this.pr.findById(id);
		
		if(productExists != null) {
			product.setId(productExists.getId());
			this.pr.save(product);
		}
		
		return null;
	}

	@Override
	public void delete(Long id) {
		Product product = this.pr.findById(id);
		
		if(product != null) {
			this.pr.delete(product);
		}
		
	}

}
