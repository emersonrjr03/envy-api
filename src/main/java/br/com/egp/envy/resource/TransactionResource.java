package br.com.egp.envy.resource;

import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.entity.TransactionEntity;
import br.com.egp.envy.groups.ValidationOnCreate;
import br.com.egp.envy.groups.ValidationOnUpdate;
import br.com.egp.envy.model.Transaction;
import br.com.egp.envy.service.TransactionService;
import br.com.egp.envy.specification.TransactionSpec;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "envelopeId",
                    value = "Transaction envelope id",
                    required = false,
                    dataType = "integer",
                    paramType = "query"
            ),
            @ApiImplicitParam(
                    name = "createdOn",
                    value = "Transaction created date",
                    required = false,
                    dataType = "date",
                    paramType = "query"
            ),
            @ApiImplicitParam(
                    name = "createdOnFrom and createdOnTo",
                    value = "Get transaction that created date is between those dates",
                    required = false,
                    dataType = "date",
                    paramType = "query"
            )
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(TransactionSpec spec) {
        return ResponseEntity.ok().body(service.findAll(spec));
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
