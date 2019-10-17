package edu.mum.studentproduct.services.datajpa;

import edu.mum.studentproduct.model.Product;
import edu.mum.studentproduct.repositories.ProductRepository;
import edu.mum.studentproduct.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jonathan on 10/16/2019.
 */

@Service
public class ProductDataJpaService implements ProductService {

	private final ProductRepository productRepository;

	public ProductDataJpaService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 *
	 * Get All Products
	 *
	 * @return
	 */
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	/**
	 *
	 * Get Product by ID
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Product findById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent()) {
			throw new RuntimeException("Product Not Found");
		}

		return productOptional.get();
	}

	/**
	 *
	 * Save or Update Product Data
	 *
	 * @param product
	 * @return
	 */
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}
}
