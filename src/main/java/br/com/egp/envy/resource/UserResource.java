package br.com.egp.envy.resource;

import br.com.egp.envy.core.exceptions.NotFoundEntityException;
import br.com.egp.envy.core.exceptions.UnnauthorizedException;
import br.com.egp.envy.dto.NewPasswordDTO;
import br.com.egp.envy.dto.NewUserDTO;
import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.mapper.UserMapper;
import br.com.egp.envy.model.User;
import br.com.egp.envy.service.UserDetailsServiceImpl;
import br.com.egp.envy.service.UserService;
import br.com.egp.envy.specification.UserSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
    @Autowired
    private UserService service;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok().body(UserMapper.unmarshall(service.findById(id)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        UserEntity userEntity = userDetailsService.getLoggedUser();
        if(userEntity.getProfiles().contains("ADMIN")) {
            return ResponseEntity.ok().body(service.findAll().stream().map(UserMapper::unmarshall).collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok().body(Arrays.asList(UserMapper.unmarshall(userEntity)));
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) throws NotFoundEntityException {
        return ResponseEntity.ok().body(service.update(user));
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> changeUserPassword(@Valid @RequestBody NewPasswordDTO user) throws NotFoundEntityException, UnnauthorizedException {
        service.changePassword(user);
        return ResponseEntity.ok().build();
    }

}
