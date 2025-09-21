package sn.bacomputer.controller;

import org.springframework.web.bind.annotation.*;
import sn.bacomputer.DTO.OrderDTO;
import sn.bacomputer.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public OrderDTO create(@RequestBody OrderDTO dto) {
        return orderService.create(dto);
    }


    @GetMapping
    public List<OrderDTO> listAll() {
        return orderService.listAll();
    }


    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable Long id) {
        return orderService.get(id);
    }
}
