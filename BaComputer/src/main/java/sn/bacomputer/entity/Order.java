package sn.bacomputer.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;
import java.util.List;


@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Users user;


    private Instant createdAt = Instant.now();


    private Double total;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items;
}
