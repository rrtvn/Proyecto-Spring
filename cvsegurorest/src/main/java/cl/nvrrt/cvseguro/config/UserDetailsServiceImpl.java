package cl.nvrrt.cvseguro.config;


import cl.nvrrt.cvseguro.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsersRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return userRepo.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
    
}
