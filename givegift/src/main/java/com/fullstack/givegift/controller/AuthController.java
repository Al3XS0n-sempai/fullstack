package com.fullstack.givegift.controller;

import com.fullstack.givegift.model.AuthCredentialRequest;
import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.model.JwtUtil;
import com.fullstack.givegift.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="/api/auth")
public class AuthController {
    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    @Autowired
    private  RegistrationService registrationService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialRequest request) {
        try {
            Authentication authenticate =  authenticationProvider.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getUsername(), request.getPassword()
              )
            );

            Gigi gigi = (Gigi) authenticate.getPrincipal();
            gigi.setPassword(null);
            return ResponseEntity.ok().header(
                    HttpHeaders.AUTHORIZATION,
                    jwtUtil.generateToken(gigi)
            ).body(gigi);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping("registration")
    public ResponseEntity<?> registration(@RequestBody AuthCredentialRequest request) {
        Optional<Gigi> gigiOpt = registrationService.createGigi(request.getUsername(), request.getPassword());
        return (gigiOpt.isPresent() ? ResponseEntity.ok().body("Аккаунт создан") :
                ResponseEntity.badRequest().body("Имя пользователя занято"));
    }
}
