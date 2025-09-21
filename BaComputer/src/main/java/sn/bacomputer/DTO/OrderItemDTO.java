package sn.bacomputer.DTO;

import lombok.*;


@Getter @Setter
public class OrderItemDTO {
    private Long productId;
    private int quantity;
    private double price;
}
