package br.com.egp.envy.model;

import br.com.egp.envy.converter.TransactionTypeConverter;
import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.enums.TransactionType;
import br.com.egp.envy.groups.ValidationOnCreate;
import br.com.egp.envy.groups.ValidationOnUpdate;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1213721L;

    @NotNull(message = "Mandatory Field", groups = { ValidationOnUpdate.class })
    private Integer id;

    @NotNull(message = "Mandatory Field", groups = { ValidationOnCreate.class, ValidationOnUpdate.class })
    private Integer envelopeId;

    @NotNull(message = "Mandatory Field",groups = { ValidationOnCreate.class, ValidationOnUpdate.class })
    private TransactionType type;

    @Size(min = 1, max = 40, groups = { ValidationOnCreate.class, ValidationOnUpdate.class })
    private String description;

    @NotNull(message = "Mandatory Field", groups = { ValidationOnCreate.class, ValidationOnUpdate.class })
    private Double amount;

    @NotNull(message = "Mandatory Field", groups = { ValidationOnCreate.class, ValidationOnUpdate.class })
    private Date createdOn;
}
