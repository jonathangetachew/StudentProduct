package edu.mum.studentproduct.controllers;

import edu.mum.studentproduct.model.Supplier;
import edu.mum.studentproduct.services.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SupplierControllerTest {

	public static final Long ID = 1L;

	@Mock
	SupplierService supplierService;

	@InjectMocks
	SupplierController supplierController;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
	}

	@Test
	void testGetAllSuppliers() throws Exception {
		//given
		Supplier supplier = new Supplier();
		supplier.setId(ID);

		Supplier supplier1 = new Supplier();
		supplier1.setId(2L);

		when(supplierService.findAll()).thenReturn(Arrays.asList(supplier, supplier1));

		//when
		mockMvc.perform(get("/suppliers"))
				//then
				.andExpect(status().isOk())
				.andExpect(view().name("supplier/list"))
				.andExpect(model().attributeExists("suppliers"));
	}

	@Test
	void testGetSupplierById() throws Exception {
		//given
		Supplier supplier = new Supplier();
		supplier.setId(ID);

		when(supplierService.findById(anyInt())).thenReturn(supplier);

		//when
		mockMvc.perform(get("/suppliers/" + ID))
				//then
				.andExpect(status().isOk())
				.andExpect(view().name("supplier/view"))
				.andExpect(model().attributeExists("supplier"));

	}

	@Test
	void testNewSupplier() throws Exception {
		//given

		//when
		mockMvc.perform(get("/suppliers/new"))
				//then
				.andExpect(status().isOk())
				.andExpect(view().name("supplier/form"))
				.andExpect(model().attributeExists("supplier"));
	}

	@Test
	void testEditSupplier() throws Exception {
		//given
		Supplier supplier = new Supplier();
		supplier.setId(ID);

		when(supplierService.findById(anyInt())).thenReturn(supplier);

		//when
		mockMvc.perform(get("/suppliers/" + ID + "/edit"))
				//then
				.andExpect(status().isOk())
				.andExpect(view().name("supplier/form"))
				.andExpect(model().attributeExists("supplier"));
	}

	@Test
	void testCreateNewSupplier() throws Exception {
		//given
		Supplier supplier = new Supplier();
		supplier.setId(ID);
		supplier.setName("Name");
		supplier.setSupplierNumber(1234);
		supplier.setContactPhoneNumber("1234567890");

		when(supplierService.save(any(Supplier.class))).thenReturn(supplier);

		//when
		mockMvc.perform(post("/supplier")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("id", "")
					.param("name", supplier.getName())
					.param("supplierNumber", supplier.getSupplierNumber().toString())
					.param("contactPhoneNumber", supplier.getContactPhoneNumber()))
				//then
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/suppliers/" + ID))
				.andExpect(model().attributeExists("supplier"));
	}

	@Test
	void testUpdateSupplier() throws Exception {
		//given
		Supplier supplier = new Supplier();
		supplier.setId(ID);
		supplier.setName("Name 2");
		supplier.setSupplierNumber(1234);
		supplier.setContactPhoneNumber("1234567890");

		when(supplierService.save(any(Supplier.class))).thenReturn(supplier);

		//when
		mockMvc.perform(post("/supplier")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("id", ID + "")
					.param("name", supplier.getName())
					.param("supplierNumber", supplier.getSupplierNumber().toString())
					.param("contactPhoneNumber", supplier.getContactPhoneNumber()))
				//then
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/suppliers/" + ID))
				.andExpect(model().attributeExists("supplier"));
	}
}