package edu.mum.studentproduct.controllers;

import edu.mum.studentproduct.model.Supplier;
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
public class SupplierController {

	private final SupplierService supplierService;

	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	/**
	 *
	 * GET Mappings
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/suppliers")
	public String getAllSuppliers(Model model) {

		model.addAttribute("suppliers", supplierService.findAll());

		return "supplier/list";
	}

	@GetMapping("/suppliers/{id}")
	public String getSupplierById(@PathVariable @Valid Integer id, Model model) {

		model.addAttribute("supplier", supplierService.findById(id));

		return "supplier/view";
	}

	@GetMapping("/suppliers/new")
	public String newSupplier(Model model) {

		model.addAttribute("supplier", new Supplier());

		return "supplier/form";
	}

	@GetMapping("/suppliers/{id}/edit")
	public String editSupplier(@PathVariable @Valid Integer id, Model model) {

		model.addAttribute("supplier", supplierService.findById(id));

		return "supplier/form";
	}

	/**
	 *
	 * POST Mappings
	 *
	 * @param supplier
	 * @return
	 */
	@PostMapping("/supplier")
	public String saveOrUpdateSupplier(@ModelAttribute @Valid Supplier supplier, Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());

			return "supplier/form";
		}

		Supplier savedSupplier = supplierService.save(supplier);

		return "redirect:/suppliers/" + savedSupplier.getId();
	}

}
