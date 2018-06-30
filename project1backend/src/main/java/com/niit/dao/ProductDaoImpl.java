package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Category;
import com.niit.model.Product;
@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory;
	public ProductDaoImpl()
	{
		System.out.println("ProductDaoImpl bean is created");
	}

	public List<Product> getallProducts() {
		Session session=sessionFactory.getCurrentSession();
		String hqlString="from Product";//Product is name of the entity
		//HQL-from Product
		//SQL-select * from product_s180396
		Query query=session.createQuery(hqlString);
		@SuppressWarnings("unchecked")
		List<Product> products=query.list();
		return products;
	}

	public Product getProduct(int id)
	{
		Session session=sessionFactory.getCurrentSession();
		//API-session.get(EntityClassObject,PK)
		//Select * from TableTPOwhich entity is mapped where PK=?
		
		Product product=(Product)session.get(Product.class, id);
		//id=1 or 2 or 3
		//Select * from product where id=1
		return product;
	}
public void deleteProduct(int id)
{
	Session session=sessionFactory.getCurrentSession();
	Product product=(Product)session.get(Product.class, id);
	//select * from product where id=1
	session.delete(product);
	//delete from product where id=1;
}
public void saveorUpdateProduct(Product product)
{
	Session session=sessionFactory.getCurrentSession();
	System.out.println("Id of the product" + product.getId());
	session.saveOrUpdate(product);//insert into product_s180396 values(?,...)
	System.out.println("after inserting id of product is" + product.getId());
}
public List<Category> getAllCategories()
{
	Session session=sessionFactory.getCurrentSession();
	//select * from category_s180396
	Query query=session.createQuery("from Category");
	List<Category> categories=query.list();
	return categories;
	
}
}
