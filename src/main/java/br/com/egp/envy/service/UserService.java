package br.com.egp.envy.service;

import br.com.egp.envy.entity.User;
import br.com.egp.envy.model.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User fromDTO(NewUserDTO objDto) {
        User user = User.builder().name(objDto.getName())
                .email(objDto.getEmail())
                .username(objDto.getUsername())
                .password(passwordEncoder.encode(objDto.getPassword()))
                .birthDate(objDto.getBirthDate())
                .build();
        return user;
    }
}
