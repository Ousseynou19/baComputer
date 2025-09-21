package sn.bacomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.bacomputer.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
