package sda.mytrip.mytrip.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserAccount findUserAccountByEmail(String email) {
        return userRepository.findUserAccountByEmail(email);
    }

    public void register(UserAccount user) {
        String encryptedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPass);
        userRepository.save(user);
    }

    public void updateUserAccount(UserAccount user){
        UserAccount oldUserAccount = findUserAccountByEmail(user.getEmail());
        if(!user.getPassword().equals("") || user.getPassword() != null){
            String encryptedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPass);
        }else{
            user.setPassword(oldUserAccount.getPassword());
        }
        userRepository.save(user);
    }

}
