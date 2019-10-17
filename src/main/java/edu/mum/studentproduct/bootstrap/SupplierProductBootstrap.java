package edu.mum.studentproduct.bootstrap;

import edu.mum.studentproduct.model.Product;
import edu.mum.studentproduct.model.Supplier;
import edu.mum.studentproduct.repositories.ProductRepository;
import edu.mum.studentproduct.repositories.SupplierRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by Jonathan on 10/9/2019.
 */

@Component
public class SupplierProductBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final SupplierRepository supplierRepository;

	private final ProductRepository productRepository;

	public SupplierProductBootstrap(SupplierRepository supplierRepository, ProductRepository productRepository) {
		this.supplierRepository = supplierRepository;
		this.productRepository = productRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadData();
	}

	private void loadData() {

		///> Create Supplier
		Supplier supplier = new Supplier();
		supplier.setSupplierNumber(1);
		supplier.setName("Walmart");
		supplier.setContactPhoneNumber("1234567890");

		///> Create Product
		Product product = new Product();
		product.setProductNumber(1L);
		product.setName("Gloves");
		product.setUnitPrice(12.00);
		product.setQuantityInStore(100);
		product.setDateSupplied(LocalDate.now());
		product.setSupplier(supplier);

		///> Create Supplier
		Supplier supplier1 = new Supplier();
		supplier1.setSupplierNumber(2);
		supplier1.setName("Target");
		supplier1.setContactPhoneNumber("0987654321");

		///> Create Product
		Product product1 = new Product();
		product1.setProductNumber(2L);
		product1.setName("Thermals");
		product1.setUnitPrice(20.00);
		product1.setQuantityInStore(150);
		product1.setDateSupplied(LocalDate.now());
		product1.setSupplier(supplier1);

		///> Create Product
		Product product2 = new Product();
		product2.setProductNumber(3L);
		product2.setName("Boots");
		product2.setUnitPrice(70.00);
		product2.setQuantityInStore(50);
		product2.setDateSupplied(LocalDate.now());
		product2.setSupplier(supplier);

		///> Save Data to DB
		supplierRepository.saveAll(Arrays.asList(supplier, supplier1));
		productRepository.saveAll(Arrays.asList(product, product1, product2));

	}
}
