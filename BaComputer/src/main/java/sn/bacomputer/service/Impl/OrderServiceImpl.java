package sn.bacomputer.service.Impl;

import org.springframework.stereotype.Service;
import sn.bacomputer.DTO.OrderDTO;
import sn.bacomputer.entity.Order;
import sn.bacomputer.entity.Users;
import sn.bacomputer.mapper.OrderMapper;
import sn.bacomputer.repository.OrderRepository;
import sn.bacomputer.repository.UserRepository;
import sn.bacomputer.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;


    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public OrderDTO create(OrderDTO dto) {
        Order order = orderMapper.toEntity(dto);
        Users user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);
//        order.setOrderDate(LocalDateTime.now());
        return orderMapper.toDTO(orderRepository.save(order));
    }


    @Override
    public List<OrderDTO> listAll() {
        return orderRepository.findAll().stream().map(orderMapper::toDTO).toList();
    }


    @Override
    public OrderDTO get(Long id) {
        return orderRepository.findById(id).map(orderMapper::toDTO).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}