package sn.bacomputer.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.bacomputer.enumerations.AccountStatus;

import java.time.LocalDateTime;
import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
    @Enumerated(EnumType.STRING)
    private AccountStatus statutCompte = AccountStatus.ACTIVE;

    private Integer failedLoginAttempts = 0;  // compteur des tentatives échouées
    private LocalDateTime lockTime;       // pour savoir quand le compte a été verrouillé

    @ManyToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();

    public boolean isEnabled() {
        return this.statutCompte == AccountStatus.ACTIVE;
    }




//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Order> orders = new ArrayList<>();
}

