package sn.bacomputer.DTO;

import lombok.*;


import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
public class OrderDTO {
    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItemDTO> items;
}
