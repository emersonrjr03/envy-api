package br.com.egp.envy.resource;

import br.com.egp.envy.dto.EmailDTO;
import br.com.egp.envy.dto.NewUserDTO;
import br.com.egp.envy.exception.UsernameAlreadyExistException;
import br.com.egp.envy.security.JwtTokenUtil;
import br.com.egp.envy.security.UserPrincipal;
import br.com.egp.envy.service.AuthService;
import br.com.egp.envy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserPrincipal user = UserService.authenticated();
        String token = jwtTokenUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
        authService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody NewUserDTO objDto) throws UsernameAlreadyExistException {
        return ResponseEntity.ok().body(userService.create(objDto));
    }
}
