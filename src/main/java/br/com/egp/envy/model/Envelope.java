package br.com.egp.envy.model;

import br.com.egp.envy.enums.EnvelopeType;
import br.com.egp.envy.groups.ValidationOnCreate;
import br.com.egp.envy.groups.ValidationOnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Envelope {
    @NotNull(message = "Mandatory Field", groups = { ValidationOnUpdate.class })
    private Integer id;
    @NotNull(message = "Mandatory Field",groups = { ValidationOnCreate.class, ValidationOnUpdate.class })
    private Integer userId;
    @Size(min = 1, max = 40, groups = { ValidationOnCreate.class, ValidationOnUpdate.class })
    private String title;
    private Double budget;
    @NotNull(message = "Mandatory Field", groups = { ValidationOnCreate.class, ValidationOnUpdate.class })
    private Double spent;
    @NotNull(message = "Mandatory Field", groups = { ValidationOnCreate.class, ValidationOnUpdate.class })
    private EnvelopeType type;
    private Double goalValue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
}
