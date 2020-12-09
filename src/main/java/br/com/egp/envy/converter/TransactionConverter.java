package br.com.egp.envy.converter;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.entity.TransactionEntity;
import br.com.egp.envy.entity.TransactionEntity;
import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.model.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionConverter implements EntityConverter<TransactionEntity, Transaction> {

    @Override
    public List<TransactionEntity> unmarshall(List<Transaction> modelList) {
        return modelList.stream().map(this::unmarshall).collect(Collectors.toList());
    }

    @Override
    public TransactionEntity unmarshall(Transaction model) {
        UserConverter userConverter = new UserConverter();

        return TransactionEntity.builder()
                .id(model.getId())
                .envelopeEntity(EnvelopeEntity.builder()
                        .id(model.getEnvelopeId())
                        .build())
                .type(model.getType())
                .description(model.getDescription())
                .amount(model.getAmount())
                .createdOn(model.getCreatedOn())
                .build();
    }

    @Override
    public List<Transaction> marshall(List<TransactionEntity> entityList) throws Exception {
        return entityList.stream().map(this::marshall).collect(Collectors.toList());
    }

    @Override
    public Transaction marshall(TransactionEntity entity) {
        UserConverter userConverter = new UserConverter();

        return Transaction.builder()
                .id(entity.getId())
                .envelopeId(entity.getEnvelopeEntity().getId())
                .type(entity.getType())
                .description(entity.getDescription())
                .amount(entity.getAmount())
                .createdOn(entity.getCreatedOn())
                .build();
    }
}
