package edu.mum.studentproduct.services.datajpa;

import edu.mum.studentproduct.model.Supplier;
import edu.mum.studentproduct.repositories.SupplierRepository;
import edu.mum.studentproduct.services.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jonathan on 10/16/2019.
 */

@Service
public class SupplierDataJpaService implements SupplierService {

	private final SupplierRepository supplierRepository;

	public SupplierDataJpaService(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	/**
	 *
	 * Get All Suppliers
	 *
	 * @return
	 */
	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	/**
	 *
	 * Get Supplier by ID
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Supplier findById(Integer id) {
		Optional<Supplier> supplierOptional = supplierRepository.findById(id.longValue());

		if (!supplierOptional.isPresent()) {
			throw new RuntimeException("Supplier Not Found");
		}

		return supplierOptional.get();
	}

	/**
	 *
	 * Save or Update Supplier Data
	 *
	 * @param supplier
	 * @return
	 */
	@Override
	public Supplier save(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
}
