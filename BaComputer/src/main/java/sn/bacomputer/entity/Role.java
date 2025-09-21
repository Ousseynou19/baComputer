package sn.bacomputer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;
    private String rolename;

    @ManyToMany(mappedBy = "roles", cascade= CascadeType.ALL)
    private Set<Users> users;



}