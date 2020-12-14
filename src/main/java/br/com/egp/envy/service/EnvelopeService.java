package br.com.egp.envy.service;

import br.com.egp.envy.converter.EnvelopeConverter;
import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.model.Envelope;
import br.com.egp.envy.repository.EnvelopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnvelopeService {

    @Autowired
    private EnvelopeRepository repository;

    private EnvelopeConverter envelopeConverter;

    public EnvelopeService() {
        this.envelopeConverter = new EnvelopeConverter();
    }

    public Envelope find(Integer id) {
        Optional<EnvelopeEntity> obj = repository.findById(id);
        return envelopeConverter.marshall(obj.orElse(null));
    }

    public List<Envelope> findAll(Specification<EnvelopeEntity> spec) {
        return repository.findAll(spec).stream().map(envelopeConverter::marshall).collect(Collectors.toList());
    }

    public Envelope update(Envelope model) {
        EnvelopeEntity entity = repository.findById(model.getId()).orElse(null);
        if(entity == null) {
            throw new EntityNotFoundException("No envelopes were found with the id: " + model.getId());
        }
        entity = repository.save(envelopeConverter.unmarshall(model));
        return envelopeConverter.marshall(entity);
    }

    public Envelope create(Envelope model) {
        EnvelopeEntity entity = repository.save(envelopeConverter.unmarshall(model));
        return envelopeConverter.marshall(entity);
    }
}
