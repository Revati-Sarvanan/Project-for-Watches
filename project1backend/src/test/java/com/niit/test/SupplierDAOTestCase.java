package com.niit.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.SupplierDao;
import com.niit.model.Supplier;



public class SupplierDAOTestCase {

 static SupplierDao supplierDAO;
 @BeforeClass
 public static void setup(){
	 @SuppressWarnings("resource")
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	 context.scan("com.niit");
	 context.refresh();
	 supplierDAO=(SupplierDao)context.getBean("supplierDAO");

}
 @Ignore
 @Test
 public void addSupplierTestCase()
 {
	 Supplier supplier=new Supplier();
	 supplier.setSupplierName("Titan");
	 supplier.setSupplierAddr("pune");
	 
	 assertTrue("Problem in Adding Supplier",supplierDAO.addSupplier(supplier));
 }
 
 @Test
 public void listSupplierDetailTest()
 {
	 
	List<Supplier> listSupplier=supplierDAO.listCategories();
	 assertNotNull("Problem in Listing",listSupplier);
	 for(Supplier supplier:listSupplier)
	 {
		 System.out.println("Supplier Name:"+supplier.getSupplierName());
		 System.out.println("Supplier Address:"+supplier.getSupplierAddr());
	 }
	 
 }
}