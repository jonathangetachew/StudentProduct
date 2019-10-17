package edu.mum.studentproduct.services;

import edu.mum.studentproduct.model.Supplier;
import edu.mum.studentproduct.repositories.SupplierRepository;
import edu.mum.studentproduct.services.datajpa.SupplierDataJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class SupplierServiceTest {

	public static final Long ID = 1L;

	@Mock
	SupplierRepository supplierRepository;

	SupplierService supplierService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		supplierService = new SupplierDataJpaService(supplierRepository);
	}

	@Test
	void testFindAll() {
		//given
		Supplier supplier = new Supplier();
		supplier.setId(ID);
		Supplier supplier1 = new Supplier();
		supplier1.setId(2L);

		when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplier, supplier1));

		//when
		List<Supplier> suppliers = supplierService.findAll();

		//then
		assertNotNull(suppliers);
		assertEquals(2, suppliers.size());
		assertEquals(ID, suppliers.get(0).getId());
	}

	@Test
	void testFindById() {
		//given
		Supplier supplier = new Supplier();
		supplier.setId(ID);

		when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(supplier));

		//when
		Supplier returnedSupplier = supplierService.findById(ID.intValue());

		//then
		assertNotNull(returnedSupplier);
		assertEquals(ID, returnedSupplier.getId());
	}

	@Test
	void testSave() {
		//given
		Supplier supplier = new Supplier();
		supplier.setId(ID);

		when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

		//when
		Supplier savedSupplier = supplierService.save(supplier);

		//then
		assertNotNull(savedSupplier);
		assertEquals(ID, savedSupplier.getId());
	}
}