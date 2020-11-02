package br.com.egp.envy.service;

import br.com.egp.envy.entity.Envelope;
import br.com.egp.envy.entity.User;
import br.com.egp.envy.repository.EnvelopeRepository;
import br.com.egp.envy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class DBService {
    @Autowired
    private EnvelopeRepository envelopeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDatabase() {
        envelopeRepository.save(Envelope.builder().id(1000001).name("Lazer").build());
        envelopeRepository.save(Envelope.builder().id(1000002).name("Desenvolvimento Pessoal").build());
        envelopeRepository.save(Envelope.builder().id(1000003).name("Contas Fixas").build());

        userRepository.save(User.builder()
                .id(1000001)
                .name("Emerson Ribeiro")
                .email("emersonrjr03@gmail.com")
                .username("emersonrjr03")
                .password(bCryptPasswordEncoder.encode("banana"))
                .birthDate(Date.valueOf("2000-10-11"))
                .build());
    }
}
