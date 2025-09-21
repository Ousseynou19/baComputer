package sn.bacomputer.repository;

import sn.bacomputer.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Users} entity.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    // Add custom query methods here

}
