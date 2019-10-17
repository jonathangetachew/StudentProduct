package edu.mum.studentproduct.repositories;

import edu.mum.studentproduct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jonathan on 10/16/2019.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
