package sn.bacomputer.entity;


import jakarta.persistence.*;
import lombok.*;


    @Entity
    @Getter @Setter @NoArgsConstructor
    public class OrderItem {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @ManyToOne
        private Order order;


        @ManyToOne
        private Product product;


        private Integer quantity;
        private Double price; // snapshot price
    }

