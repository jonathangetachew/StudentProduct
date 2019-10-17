package edu.mum.studentproduct.controllers;

import edu.mum.studentproduct.model.Product;
import edu.mum.studentproduct.services.ProductService;
import edu.mum.studentproduct.services.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by Jonathan on 10/16/2019.
 */

@Controller
public class ProductController {

	private final ProductService productService;

	private final SupplierService supplierService;

	public ProductController(ProductService productService, SupplierService supplierService) {
		this.productService = productService;
		this.supplierService = supplierService;
	}

	/**
	 *
	 * GET Mappings
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/products")
	public String getAllProducts(Model model) {

		model.addAttribute("products", productService.findAll());

		return "product/list";
	}

	@GetMapping("/products/{id}")
	public String getProductById(@PathVariable @Valid Long id, Model model) {

		model.addAttribute("product", productService.findById(id));

		return "product/view";
	}

	@GetMapping("/products/new")
	public String newProduct(Model model) {

		model.addAttribute("product", new Product());
		// Add suppliers for the dropdown
		model.addAttribute("suppliers", supplierService.findAll());

		return "product/form";
	}

	@GetMapping("/products/{id}/edit")
	public String editProduct(@PathVariable @Valid Long id, Model model) {

		model.addAttribute("product", productService.findById(id));
		// Add suppliers for the dropdown
		model.addAttribute("suppliers", supplierService.findAll());

		return "product/form";
	}

	/**
	 *
	 * POST Mappings
	 *
	 * @param product
	 * @return
	 */
	@PostMapping("/product")
	public String saveOrUpdateProduct(@ModelAttribute @Valid Product product, Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			// Add suppliers for the dropdown
			model.addAttribute("suppliers", supplierService.findAll());

			return "product/form";
		}

		Product savedProduct = productService.save(product);

		return "redirect:/products/" + savedProduct.getId();
	}

}
