package br.com.egp.envy.mapper;

import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.model.User;

public class UserMapper {
    public UserMapper(){
        super();
    }
    public static UserEntity marshall(User user){
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    public static User unmarshall(UserEntity user){
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
