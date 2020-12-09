package br.com.egp.envy.entity;

import br.com.egp.envy.converter.EnvelopeTypeConverter;
import br.com.egp.envy.converter.TransactionTypeConverter;
import br.com.egp.envy.enums.EnvelopeType;
import br.com.egp.envy.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity()
@Table(name = "TRANSACTION")
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1213721L;

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENVELOPE", referencedColumnName = "ID")
    private EnvelopeEntity envelopeEntity;

    @Convert(converter = TransactionTypeConverter.class)
    @Column(name = "TYPE", nullable = false)
    private TransactionType type;

    @Column(name = "TITLE", nullable = false)
    private String description;

    @Column(name = "AMOUNT", nullable = true)
    private Double amount;

    @Column(name = "CREATED_ON", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
}
