package sn.bacomputer.security;//package sn.systalink.security;
//
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import sn.systalink.entity.Users;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.stream.Collectors;
//
//public class CustomUserDetails implements UserDetails {
//    private Users user;
//
//    public CustomUserDetails(Users user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isEnabled() {
//        return user.isActive();
//    }
//
//    // Getter pour récupérer l'objet Users si nécessaire
//    public Users getUser() {
//        return user;
//    }
//}
//
