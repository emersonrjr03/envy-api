package br.com.egp.envy.service;

import br.com.egp.envy.core.exceptions.NotFoundEntityException;
import br.com.egp.envy.core.exceptions.UnnauthorizedException;
import br.com.egp.envy.dto.NewPasswordDTO;
import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.dto.NewUserDTO;
import br.com.egp.envy.exception.UsernameAlreadyExistException;
import br.com.egp.envy.mapper.UserMapper;
import br.com.egp.envy.model.User;
import br.com.egp.envy.repository.UserRepository;
import br.com.egp.envy.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    public User create(NewUserDTO dto) throws UsernameAlreadyExistException {
        UserEntity entity = userRepository.findByUsernameOrEmail(dto.getUsername());
        if(entity != null) {
            throw new UsernameAlreadyExistException("The username " + dto.getUsername() + " is already been used!", null, "username");
        }
        return UserMapper.unmarshall(userRepository.save(fromDTO(dto)));
    }

    public User update(User user) throws NotFoundEntityException {
        UserEntity entity = userRepository.getOne(user.getId());
        if(entity == null){
            throw new NotFoundEntityException("Usuário não encontrado (id:" + user.getId() +")");
        }

        entity.setBirthDate(user.getBirthDate() != null ? user.getBirthDate() : entity.getBirthDate());
        entity.setEmail(user.getEmail() != null ? user.getEmail() : entity.getEmail());
        entity.setName(user.getName() != null ? user.getName() : entity.getName());
        entity.setUsername(user.getUsername() != null ? user.getUsername() : entity.getUsername());
        return UserMapper.unmarshall(userRepository.save(entity));
    }

    public void changePassword(NewPasswordDTO dto) throws NotFoundEntityException, UnnauthorizedException {
        UserPrincipal up = authenticated();
        UserEntity entity = userRepository.getOne(up.getId());
        if(entity == null){
            throw new NotFoundEntityException("Usuário não encontrado (id:" + up.getId() +")");
        }
        authService.updatePassword(entity, dto);
    }

    public static UserPrincipal authenticated() {
        try {
            return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }

    public UserEntity fromDTO(NewUserDTO objDto) {
        UserEntity userEntity = UserEntity.builder().name(objDto.getName())
                .email(objDto.getEmail())
                .username(objDto.getUsername())
                .password(passwordEncoder.encode(objDto.getPassword()))
                .birthDate(objDto.getBirthDate())
                .build();
        return userEntity;
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }
}
