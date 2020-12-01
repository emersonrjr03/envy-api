package br.com.egp.envy.service;

import br.com.egp.envy.core.exceptions.UnnauthorizedException;
import br.com.egp.envy.dto.NewPasswordDTO;
import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void updatePassword(UserEntity entity, NewPasswordDTO dto) throws UnnauthorizedException {

        if(!bCryptPasswordEncoder.matches(dto.getPreviousPassword(), entity.getPassword())) {
            throw new UnnauthorizedException("Credenciais inválidas!");
        }
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getNewPassword()));
        userRepository.save(entity);
    }

    public void sendNewPassword(String email) {
        UserEntity userEntity = userRepository.findByUsernameOrEmail(email);
        if(userEntity == null) {
            throw new ObjectNotFoundException(email, "User");
        }
        String newPass = newPassword();
        userEntity.setPassword(bCryptPasswordEncoder.encode(newPass));
        userRepository.save(userEntity);
        emailService.sendNewPasswordHtmlEmail(userEntity, newPass);

    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0 ){ //gera numeros
            return (char) (random.nextInt(10) + 48);
        } else if (opt == 1) { //gera letra maiuscula
            return (char) (random.nextInt(26) + 65);
        } else { //gera letra minuscula
            return (char) (random.nextInt(26) + 97);
        }
    }
}
