package sn.bacomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.bacomputer.entity.Users;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
}
