package br.com.egp.envy.repository;

import br.com.egp.envy.entity.EnvelopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

public interface EnvelopeRepository extends PagingAndSortingRepository<EnvelopeEntity, Integer>, JpaSpecificationExecutor<EnvelopeEntity> {

}
