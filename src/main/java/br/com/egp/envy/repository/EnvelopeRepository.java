package br.com.egp.envy.repository;

import br.com.egp.envy.entity.EnvelopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvelopeRepository extends JpaRepository<EnvelopeEntity, Integer> {

}
