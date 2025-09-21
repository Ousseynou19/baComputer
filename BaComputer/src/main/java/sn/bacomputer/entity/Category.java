package sn.bacomputer.entity;



import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter @NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    private String name;
}
