package br.com.egp.envy.repository;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface TransactionRepository extends PagingAndSortingRepository<TransactionEntity, Integer>, JpaSpecificationExecutor<TransactionEntity> {

}
