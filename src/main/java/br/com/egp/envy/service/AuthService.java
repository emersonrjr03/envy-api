package br.com.egp.envy.service;

import br.com.egp.envy.entity.User;
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

    public void sendNewPassword(String email) {
        User user = userRepository.findByUsernameOrEmail(email);
        if(user == null) {
            throw new ObjectNotFoundException(email, "User");
        }
        String newPass = newPassword();
        user.setPassword(bCryptPasswordEncoder.encode(newPass));
        emailService.sendNewPasswordEmail(user, newPass);
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
