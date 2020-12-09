package br.com.egp.envy.converter;

import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter implements EntityConverter<UserEntity, User> {
    @Override
    public List<UserEntity> unmarshall(List<User> modelList) {
        return modelList.stream().map(this::unmarshall).collect(Collectors.toList());
    }

    @Override
    public UserEntity unmarshall(User model) {
        return UserEntity.builder()
                .id(model.getId())
                .username(model.getUsername())
                .birthDate(model.getBirthDate())
                .name(model.getName())
                .email(model.getEmail())
                .build();
    }

    @Override
    public List<User> marshall(List<UserEntity> entityList) throws Exception {
        return null;
    }

    @Override
    public User marshall(UserEntity entity) {
        return null;
    }
}
