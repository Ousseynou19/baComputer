package sn.bacomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.bacomputer.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
