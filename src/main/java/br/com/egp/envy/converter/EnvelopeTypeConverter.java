package br.com.egp.envy.converter;

import br.com.egp.envy.enums.EnvelopeType;

import javax.persistence.AttributeConverter;

public class EnvelopeTypeConverter  implements AttributeConverter<EnvelopeType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EnvelopeType attribute) {
        if(attribute != null) {
            return attribute.getCode();
        }
        return null;
    }

    @Override
    public EnvelopeType convertToEntityAttribute(Integer dbData) {
        if(dbData != null) {
            return EnvelopeType.toEnum(dbData);
        }
        return null;
    }
}