package br.com.egp.envy.entity;

import br.com.egp.envy.converter.EnvelopeTypeConverter;
import br.com.egp.envy.enums.EnvelopeType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity()
@Table(name = "ENVELOPE")
public class EnvelopeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ENVY", referencedColumnName = "ID")
    private UserEntity user;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "BUDGET", nullable = true)
    private Double budget;

    @Column(name = "SPENT", nullable = false)
    private Double spent;

    @Convert(converter = EnvelopeTypeConverter.class)
    @Column(name = "TYPE", nullable = false)
    private EnvelopeType type;

    @Column(name = "GOAL_VALUE", nullable = true)
    private Double goalValue;

    @Column(name = "DUE_DATE", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dueDate;


}
