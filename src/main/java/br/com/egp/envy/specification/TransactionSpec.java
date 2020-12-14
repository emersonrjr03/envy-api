package br.com.egp.envy.specification;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.entity.TransactionEntity;
import io.swagger.annotations.ApiModel;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Join(path= "envelopeEntity", alias = "env")
@And({
        @Spec(path="env.id", params="envelopeId", spec=Equal.class),
        @Spec(path="createdOn", params="createdOn", spec=Equal.class),
        @Spec(path="createdOn", params={"createdOnFrom","createdOnTo"}, spec= Between.class, config="yyyy-MM-dd")
})
public interface TransactionSpec extends Specification<TransactionEntity> {

}
