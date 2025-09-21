package sn.bacomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.bacomputer.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
