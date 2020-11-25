package br.com.egp.envy.model;

import br.com.egp.envy.enums.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String email;

    private String username;

    private Date birthDate;

    private Set<Integer> profiles = new HashSet<>();

    public User() {
        super();
        addProfile(UserProfile.USER);
    }

    @Builder
    public User(Integer id, String name, String email, String username, Date birthDate) {
        this();
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.birthDate = birthDate;
    }
    public Set<UserProfile> getProfiles() {
        return profiles.stream().map(x -> UserProfile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(UserProfile userProfile) {
        profiles.add(userProfile.getCod());
    }

}
