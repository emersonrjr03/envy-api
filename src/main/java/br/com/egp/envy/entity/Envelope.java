package br.com.egp.envy.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Envelope implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private String name;
}
