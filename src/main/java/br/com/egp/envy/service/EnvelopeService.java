package br.com.egp.envy.service;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.repository.EnvelopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvelopeService {

    @Autowired
    private EnvelopeRepository repository;

    public EnvelopeEntity find(Integer id) {
        Optional<EnvelopeEntity> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public List<EnvelopeEntity> findAll() {
        return repository.findAll();
    }

}
