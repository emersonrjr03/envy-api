package br.com.egp.envy.converter;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.model.Envelope;

import java.util.List;
import java.util.stream.Collectors;

public class EnvelopeConverter implements EntityConverter<EnvelopeEntity, Envelope> {

    @Override
    public List<EnvelopeEntity> unmarshall(List<Envelope> modelList) {
        return modelList.stream().map(this::unmarshall).collect(Collectors.toList());
    }

    @Override
    public EnvelopeEntity unmarshall(Envelope model) {
        UserConverter userConverter = new UserConverter();

        return EnvelopeEntity.builder()
                .id(model.getId())
                .title(model.getTitle())
                .user(UserEntity.builder()
                        .id(model.getUserId())
                        .build())
                .budget(model.getBudget())
                .spent(model.getSpent())
                .type(model.getType())
                .goalValue(model.getGoalValue())
                .dueDate(model.getDueDate())
                .build();
    }

    @Override
    public List<Envelope> marshall(List<EnvelopeEntity> entityList) throws Exception {
        return entityList.stream().map(this::marshall).collect(Collectors.toList());
    }

    @Override
    public Envelope marshall(EnvelopeEntity entity) {
        UserConverter userConverter = new UserConverter();

        return Envelope.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .title(entity.getTitle())
                .budget(entity.getBudget())
                .spent(entity.getSpent())
                .type(entity.getType())
                .goalValue(entity.getGoalValue())
                .dueDate(entity.getDueDate())
                .build();
    }
}
