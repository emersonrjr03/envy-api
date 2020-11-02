package br.com.egp.envy.resource;

import br.com.egp.envy.service.EnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
