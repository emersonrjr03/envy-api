package br.com.egp.envy.converter;

import br.com.egp.envy.enums.EnvelopeType;
import br.com.egp.envy.enums.TransactionType;

import javax.persistence.AttributeConverter;

public class TransactionTypeConverter implements AttributeConverter<TransactionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TransactionType attribute) {
        if(attribute != null) {
            return attribute.getCode();
        }
        return null;
    }

    @Override
    public TransactionType convertToEntityAttribute(Integer dbData) {
        if(dbData != null) {
            return TransactionType.toEnum(dbData);
        }
        return null;
    }
}