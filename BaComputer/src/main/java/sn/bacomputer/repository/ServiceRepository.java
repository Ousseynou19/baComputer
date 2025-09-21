package sn.bacomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;import sn.bacomputer.entity.ServiceEntity;


public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
