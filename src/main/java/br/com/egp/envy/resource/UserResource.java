package br.com.egp.envy.resource;

import br.com.egp.envy.core.exceptions.NotFoundEntityException;
import br.com.egp.envy.core.exceptions.UnnauthorizedException;
import br.com.egp.envy.dto.NewPasswordDTO;
import br.com.egp.envy.dto.NewUserDTO;
import br.com.egp.envy.model.User;
import br.com.egp.envy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) throws NotFoundEntityException {
        return ResponseEntity.ok().body(service.update(user));
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> changeUserPassword(@Valid @RequestBody NewPasswordDTO user) throws NotFoundEntityException, UnnauthorizedException {
        service.changePassword(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
