package br.com.egp.envy.specification;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.entity.UserEntity;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path="id", pathVars="id", spec=Equal.class),
})
public interface UserSpec extends Specification<UserEntity> {

}
