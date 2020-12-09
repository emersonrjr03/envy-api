package br.com.egp.envy.service;

import br.com.egp.envy.converter.EnvelopeConverter;
import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.model.Envelope;
import br.com.egp.envy.repository.EnvelopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Envelope> findAll() {
        return repository.findAll().stream().map(envelopeConverter::marshall).collect(Collectors.toList());
    }

    public Envelope update(Envelope envelope) {
        EnvelopeEntity entity = repository.save(envelopeConverter.unmarshall(envelope));
        return envelopeConverter.marshall(entity);
    }

    public Object create(Envelope envelope) {
        EnvelopeEntity entity = repository.save(envelopeConverter.unmarshall(envelope));
        return envelopeConverter.marshall(entity);
    }
}
