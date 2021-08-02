package br.com.gleiston.restapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Cant no be empty")
	@NotBlank(message = "Cant no be blank")
	@Size(min = 4, max = 255)
	private String name;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 1000)
	private Integer qtd;
	
	@NotNull
	@Min(value = 0)
	private Double price;
	
	private Date dateCreated;
	
	public Product() {
	}

	public Product(Long id, String name, Integer qtd, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.qtd = qtd;
		this.price = price;
	}

	public Product(String name, Integer qtd, Double price) {
		super();
		this.name = name;
		this.qtd = qtd;
		this.price = price;
	}
	
	@PrePersist
	public void onPrePesist() {
		if(this.dateCreated == null) {
			this.dateCreated = new Date();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", qtd=" + qtd + ", price=" + price + ", dateCreated="
				+ dateCreated + "]";
	}
	
	
}
