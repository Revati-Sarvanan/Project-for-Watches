package com.niit.configuration;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.SupplierDao;
import com.niit.dao.SupplierDaoImpl;
import com.niit.model.Authorities;
import com.niit.model.BillingAddress;
import com.niit.model.Cart;
import com.niit.model.CartItem;
import com.niit.model.Category;
import com.niit.model.Customer;
import com.niit.model.CustomerOrder;
import com.niit.model.Product;
import com.niit.model.ShippingAddress;
import com.niit.model.Supplier;
import com.niit.model.User;

@Configuration
@EnableTransactionManagement    //commit  /rollback

	public class DBConfiguration {
		//to create beans
			
			@Bean(name="dataSource")
			public DataSource getDataSource() {
			    BasicDataSource dataSource = new BasicDataSource();
			    dataSource.setDriverClassName("org.h2.Driver");
			    dataSource.setUrl("jdbc:h2:tcp://localhost/~/Revati");
			    dataSource.setUsername("sa");
			    dataSource.setPassword("");
			    return dataSource;
			}
			
			@Bean(name="supplierDAO")
			public SupplierDao getSupplierDAO()
			{
				System.out.println("---Supplier Bean Created---");
				return new SupplierDaoImpl();
			}
			/*
			 * <bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBuilder">
			 * <property name="dataSource" ref="dataSource">
			 */
			@Bean //SessionFactory - factory of session objects
			public SessionFactory sessionFactory()
			{    
				System.out.println("Entering sessionFactory creation method");
				LocalSessionFactoryBuilder lsf=
						new LocalSessionFactoryBuilder(getDataSource());
				Properties hibernateProperties=new Properties();
				hibernateProperties.setProperty(
						"hibernate.dialect", "org.hibernate.dialect.H2Dialect");
				hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
				hibernateProperties.setProperty("hibernate.show_sql", "true");
				lsf.addProperties(hibernateProperties);
				//An array of Class objects of all the entities
				//Map all entities to relational table
				@SuppressWarnings("rawtypes")
				Class classes[]=new Class[]{CustomerOrder.class,Product.class,Category.class,Supplier.class,Customer.class,User.class,BillingAddress.class,ShippingAddress.class,Authorities.class,Cart.class,CartItem.class};
				//localsessionfactorybuilder -> sessionfactory -> map all entities with relation table
				System.out.println("SessionFactory bean"+ lsf );
			    return lsf.addAnnotatedClasses(classes).buildSessionFactory();
			}
			@Bean
			public HibernateTransactionManager hibTransManagement(){
				return new HibernateTransactionManager(sessionFactory());
			}
		}



