package sn.bacomputer.service;

import sn.bacomputer.DTO.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO create(OrderDTO dto);

    List<OrderDTO> listAll();

    OrderDTO get(Long id);
}
