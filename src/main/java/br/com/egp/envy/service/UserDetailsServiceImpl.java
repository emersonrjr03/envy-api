package br.com.egp.envy.service;

import br.com.egp.envy.entity.User;
import br.com.egp.envy.repository.UserRepository;
import br.com.egp.envy.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class  UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email, used on JWTAuthenticationFilter
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }

        return UserPrincipal.create(user);
    }

    // This method can be used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public User getLoggedUser(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User principal = null;
        if(userPrincipal != null || !"".equals(userPrincipal.getUsername())){
            principal = userRepository.findById(userPrincipal.getId()).orElse(null);
        }
        return principal;
    }
}
