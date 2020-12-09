package br.com.egp.envy.service;

import br.com.egp.envy.converter.TransactionConverter;
import br.com.egp.envy.entity.TransactionEntity;
import br.com.egp.envy.model.Transaction;
import br.com.egp.envy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    private TransactionConverter TransactionConverter;

    public TransactionService() {
        this.TransactionConverter = new TransactionConverter();
    }

    public Transaction find(Integer id) {
        Optional<TransactionEntity> obj = repository.findById(id);
        return TransactionConverter.marshall(obj.orElse(null));
    }

    public List<Transaction> findAll() {
        return repository.findAll().stream().map(TransactionConverter::marshall).collect(Collectors.toList());
    }

    public Transaction update(Transaction model) {
        TransactionEntity entity = repository.findById(model.getId()).orElse(null);
        if(entity == null) {
            throw new EntityNotFoundException("No Transactions were found with the id: " + model.getId());
        }
        entity = repository.save(TransactionConverter.unmarshall(model));
        return TransactionConverter.marshall(entity);
    }

    public Transaction create(Transaction model) {
        TransactionEntity entity = repository.save(TransactionConverter.unmarshall(model));
        return TransactionConverter.marshall(entity);
    }
}
