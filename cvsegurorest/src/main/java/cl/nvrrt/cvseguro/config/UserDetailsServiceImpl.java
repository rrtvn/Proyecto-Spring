package cl.nvrrt.cvseguro.config;

import java.util.Optional;

import cl.nvrrt.cvseguro.entities.User;
import cl.nvrrt.cvseguro.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsersRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepo.findByEmail( username )
            .orElseThrow(() -> new UsernameNotFoundException(
                "User Not Found with -> username or email : " + username
            ));

        return UserDetailsImpl.build(user);
    }
    
}
