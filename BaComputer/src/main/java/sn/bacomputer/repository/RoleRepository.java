package sn.bacomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.bacomputer.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role,Long> {
    Optional<Role> findByRolename(String rolename);

}
