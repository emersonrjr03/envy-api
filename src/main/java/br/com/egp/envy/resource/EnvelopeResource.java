package br.com.egp.envy.resource;

import br.com.egp.envy.core.exceptions.NotFoundEntityException;
import br.com.egp.envy.core.exceptions.UnnauthorizedException;
import br.com.egp.envy.dto.NewPasswordDTO;
import br.com.egp.envy.entity.EnvelopeEntity;
import br.com.egp.envy.groups.ValidationOnCreate;
import br.com.egp.envy.groups.ValidationOnUpdate;
import br.com.egp.envy.model.Envelope;
import br.com.egp.envy.model.User;
import br.com.egp.envy.service.EnvelopeService;
import br.com.egp.envy.specification.EnvelopeSpec;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "userId",
                    value = "Envelope user id",
                    required = false,
                    dataType = "number",
                    paramType = "query"
            ),
            @ApiImplicitParam(
                    name = "title",
                    value = "Envelope title",
                    required = false,
                    dataType = "string",
                    paramType = "query"
            )
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(EnvelopeSpec spec) {
        return ResponseEntity.ok().body(service.findAll(spec));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Validated(ValidationOnCreate.class) @RequestBody Envelope envelope) {
        return ResponseEntity.ok().body(service.create(envelope));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Validated(ValidationOnUpdate.class) @RequestBody Envelope envelope) {
        return ResponseEntity.ok().body(service.update(envelope));
    }
}
