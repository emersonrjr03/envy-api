package br.com.egp.envy.repository;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.model.Envelope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface EnvelopeRepository extends PagingAndSortingRepository<EnvelopeEntity, Integer>, JpaSpecificationExecutor<EnvelopeEntity> {

    List<EnvelopeEntity> findAllByUser(UserEntity loggedUser);
}
