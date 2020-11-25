package br.com.egp.envy.service;

import br.com.egp.envy.entity.UserEntity;
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
        UserEntity userEntity = userRepository.findByUsernameOrEmail(usernameOrEmail);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }

        return UserPrincipal.create(userEntity);
    }

    // This method can be used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(userEntity);
    }

    @Transactional
    public UserEntity getLoggedUser(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity principal = null;
        if(userPrincipal != null || !"".equals(userPrincipal.getUsername())){
            principal = userRepository.findById(userPrincipal.getId()).orElse(null);
        }
        return principal;
    }
}
