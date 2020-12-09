package br.com.egp.envy.service;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.enums.EnvelopeType;
import br.com.egp.envy.enums.UserProfile;
import br.com.egp.envy.repository.EnvelopeRepository;
import br.com.egp.envy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

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

        UserEntity entity = userRepository.save(UserEntity.builder()
                .name("Emerson Ribeiro")
                .email("emersonrjr03@gmail.com")
                .username("emersonrjr03")
                .password(bCryptPasswordEncoder.encode("banana"))
                .birthDate(Date.valueOf("2000-10-11"))
                .build());

        envelopeRepository.save(EnvelopeEntity.builder()
                .user(entity)
                .type(EnvelopeType.BUDGET)
                .title("LAZER")
                .budget(500.0)
                .spent(0.0)
                .build());
        envelopeRepository.save(EnvelopeEntity.builder()
                .user(entity)
                .type(EnvelopeType.GOAL)
                .title("MALDIVAS")
                .dueDate(DateUtils.create(2021, 12, 01).getTime())
                .goalValue(12500.0)
                .spent(0.0)
                .build());
        envelopeRepository.save(EnvelopeEntity.builder()
                .user(entity)
                .type(EnvelopeType.BUDGET)
                .title("CONTAS FIXAS")
                .budget(305.0)
                .spent(0.0)
                .build());
    }
}
