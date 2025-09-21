package sn.bacomputer.repository;

import sn.bacomputer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Product} entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Add custom query methods here

}
