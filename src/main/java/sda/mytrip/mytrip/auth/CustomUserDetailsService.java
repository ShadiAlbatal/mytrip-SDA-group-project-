package sda.mytrip.mytrip.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sda.mytrip.mytrip.user.UserAccount;
import sda.mytrip.mytrip.user.UserAccountRepository;


import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserAccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Email is used as the username in this case
        UserAccount user = userRepository.findUserAccountByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
