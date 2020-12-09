package br.com.egp.envy.resource;

import br.com.egp.envy.core.exceptions.NotFoundEntityException;
import br.com.egp.envy.core.exceptions.UnnauthorizedException;
import br.com.egp.envy.dto.NewPasswordDTO;
import br.com.egp.envy.groups.ValidationOnCreate;
import br.com.egp.envy.model.Envelope;
import br.com.egp.envy.model.User;
import br.com.egp.envy.service.EnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/envelope")
public class EnvelopeResource {
    @Autowired
    private EnvelopeService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.find(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Validated(ValidationOnCreate.class) @RequestBody Envelope envelope) {
        return ResponseEntity.ok().body(service.create(envelope));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Validated(ValidationOnCreate.class) @RequestBody Envelope envelope) {
        return ResponseEntity.ok().body(service.update(envelope));
    }
}
