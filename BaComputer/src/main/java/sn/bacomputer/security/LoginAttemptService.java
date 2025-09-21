package sn.bacomputer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.bacomputer.entity.Users;
import sn.bacomputer.enumerations.AccountStatus;
import sn.bacomputer.repository.UserRepository;


import java.time.LocalDateTime;

@Service
public class LoginAttemptService {

    @Autowired
    private UserRepository usersRepository;

    public void increaseFailedAttempts(Users user) {
        int newFailAttempts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(newFailAttempts);

        if (newFailAttempts >= SecurityConstants.MAX_FAILED_ATTEMPTS) {
            lock(user);
        }

        usersRepository.save(user);
    }

    public void resetFailedAttempts(Users user) {
        user.setFailedLoginAttempts(0);
        user.setLockTime(null);
        usersRepository.save(user);
    }

    private void lock(Users user) {
        user.setStatutCompte(AccountStatus.LOCKED);
        user.setLockTime(LocalDateTime.now());
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

