package com.niit.dao;

import java.util.List;

import com.niit.model.Category;
import com.niit.model.Product;

public interface ProductDao {
	List<Product> getallProducts();
	Product getProduct(int id);
    void deleteProduct(int id);
    void saveorUpdateProduct(Product product);
    List<Category> getAllCategories();
}
