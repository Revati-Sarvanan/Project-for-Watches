package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity //mapped with product relational table
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotEmpty(message="ProductName cannot be blank(*)")
	private String productname;
	@NotEmpty(message="Product Description is required(*)")
	private String productdescription;
	@Min(value=1,message="minimum quantity must be 1")
	private int quantity;
	@Min(value=1,message="minimum price must be 1")
	private double price;
	@ManyToOne
	@JoinColumn(name="cid")
	//FK category_id
	private Category category;
	@Transient //not persistent
	private MultipartFile image;	
	
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public int getId() {
		System.out.println("Get Id");
		return id;
	}
	public void setId(int id) {
		System.out.println("set Id");
		this.id = id;
	}
	public String getProductname() {
		System.out.println("Get Productname");
		return productname;
	}
	public void setProductname(String productname) {
		System.out.println("set productname");
		this.productname = productname;
	}
	public String getProductdescription() {
		System.out.println("Get product description");
		return productdescription;
	}
	public void setProductdescription(String productdescription) {
		System.out.println("set product description");
		this.productdescription = productdescription;
	}
	public int getQuantity() {
		System.out.println("Get quantity");
		return quantity;
	}
	public void setQuantity(int quantity) {
		System.out.println("set quantity");
		this.quantity = quantity;
	}
	public double getPrice() {
		System.out.println("Get price");
		return price;
	}
	public void setPrice(double price) {
		System.out.println("set price");
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
@Override
public String toString()
{
	return "[" + this.id + " "+ this.productname + " "+this.productdescription + " "+ this.price + " " + this.quantity + "]";
}
}
