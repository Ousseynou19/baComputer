package sn.bacomputer.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.bacomputer.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
