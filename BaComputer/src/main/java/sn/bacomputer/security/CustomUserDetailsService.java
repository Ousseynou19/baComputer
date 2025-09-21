package sn.bacomputer.security;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sn.bacomputer.entity.Users;
import sn.bacomputer.enumerations.AccountStatus;
import sn.bacomputer.repository.UserRepository;


import java.time.LocalDateTime;

;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserRepository usersRepository;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
//	{
//		org.springframework.security.core.userdetails.User springUser= null;
//
//		Users user = usersRepository.findByUsername(username)
//				.orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
//		if (user==null)
//			throw new UsernameNotFoundException("utilisateur inexistant !!!!");
//		else
//		{
//			Set<Role> roles = user.getRoles();
//			List<String> noms_roles = new ArrayList<String>();
//			for ( Role r : roles)
//				noms_roles.add(r.getRolename());
//
//			Set<GrantedAuthority> ga = new HashSet<GrantedAuthority>();
//			for (String nr : noms_roles)
//				ga.add(new SimpleGrantedAuthority(nr));
////		   if (user.isFirstConnect()) {
////			   throw new RuntimeException("Vous devez changer votre mot de passe avant de continuer.");
////		   }
//			springUser = new org.springframework.security.core.userdetails.User(
//					user.getUsername(),
//					user.getPassword(),
//					user.isEnabled(),      // 🔑 ACTIVE uniquement
//					true,                  // accountNonExpired
//					true,                  // credentialsNonExpired
//					user.getStatutCompte() != AccountStatus.LOCKED, // 🔑 LOCKED → false
//				    ga
//			);
//
//
//
//			return springUser;
//
//		}
//	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + username));

		return UserDetailsImpl.build(user); // ✅ on retourne ton impl
	}
	public void increaseFailedAttempts(Users user) {
		int newFailAttempts = user.getFailedLoginAttempts() + 1;
		user.setFailedLoginAttempts(newFailAttempts);

		if (newFailAttempts >= 5) {
			user.setStatutCompte(AccountStatus.LOCKED);
			user.setLockTime(LocalDateTime.now());
		}

		usersRepository.save(user);
	}

	public void resetFailedAttempts(Users user) {
		user.setFailedLoginAttempts(0);
		usersRepository.save(user);
	}


	public boolean unlockIfTimeExpired(Users user) {
		if (user.getStatutCompte() == AccountStatus.LOCKED) {
			if (user.getLockTime() != null) {
				LocalDateTime unlockTime = user.getLockTime().plusMinutes(SecurityConstants.LOCK_TIME_DURATION);
				if (LocalDateTime.now().isAfter(unlockTime)) {
					user.setStatutCompte(AccountStatus.ACTIVE);
					resetFailedAttempts(user);
					usersRepository.save(user);
					return true;
				}
			}
		}
		return false;
	}


}
