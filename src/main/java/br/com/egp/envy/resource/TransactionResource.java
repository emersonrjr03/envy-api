package br.com.egp.envy.resource;

import br.com.egp.envy.groups.ValidationOnCreate;
import br.com.egp.envy.groups.ValidationOnUpdate;
import br.com.egp.envy.model.Transaction;
import br.com.egp.envy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionResource {
    @Autowired
    private TransactionService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.find(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Validated(ValidationOnCreate.class) @RequestBody Transaction Transaction) {
        return ResponseEntity.ok().body(service.create(Transaction));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Validated(ValidationOnUpdate.class) @RequestBody Transaction Transaction) {
        return ResponseEntity.ok().body(service.update(Transaction));
    }
}
