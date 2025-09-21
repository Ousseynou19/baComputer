package sn.bacomputer.repository;

import sn.bacomputer.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Service} entity.
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    // Add custom query methods here

}
