package sn.bacomputer.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sn.bacomputer.entity.Role;
import sn.bacomputer.entity.Users;
import sn.bacomputer.enumerations.AccountStatus;


import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
//    private String email;
    private String password;
    private boolean enabled;
    private AccountStatus statutCompte;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password,
                           boolean enabled, AccountStatus statutCompte,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
//        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.statutCompte = statutCompte;
        this.authorities = authorities;
    }

    // Factory method
    public static UserDetailsImpl build(Users user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(Role::getRolename)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
//                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.getStatutCompte(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

//    public String getEmail() {
//        return email;
//    }

    public AccountStatus getStatutCompte() {
        return statutCompte;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return statutCompte != AccountStatus.LOCKED; // 🔑 si LOCKED → false
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled; // 🔑 dépend du champ enabled
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailsImpl)) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
