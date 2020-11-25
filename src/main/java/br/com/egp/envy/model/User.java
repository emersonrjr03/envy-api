package br.com.egp.envy.entity;

import br.com.egp.envy.enums.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "USER_ENVY")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String name;

    private String email;

    private String username;

    @JsonIgnore
    private String password;

    private Date birthDate;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_PROFILE")
    private Set<Integer> profiles = new HashSet<>();

    public UserEntity() {
        super();
        addProfile(UserProfile.USER);
    }

    @Builder
    public UserEntity(Integer id, String name, String email, String username, String password, Date birthDate) {
        this();
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
    }
    public Set<UserProfile> getProfiles() {
        return profiles.stream().map(x -> UserProfile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(UserProfile userProfile) {
        profiles.add(userProfile.getCod());
    }

}
