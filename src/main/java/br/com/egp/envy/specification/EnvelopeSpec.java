package br.com.egp.envy.specification;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.model.Envelope;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
@Join(path= "user", alias = "user")
@And({
        @Spec(path="id", pathVars="id", spec=Equal.class),
        @Spec(path="user.id", params="userId", spec=Equal.class),
        @Spec(path="title", params="title", spec= LikeIgnoreCase.class)
})
public interface EnvelopeSpec extends Specification<EnvelopeEntity> {

}
