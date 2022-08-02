package com.sapo.ex8_spring_security.controller;

import com.sapo.ex8_spring_security.model.entity.Account;
import com.sapo.ex8_spring_security.model.dto.request.LoginRequest;
import com.sapo.ex8_spring_security.model.dto.respond.LoginRespond;
import com.sapo.ex8_spring_security.security.JwtTokenUtil;
import com.sapo.ex8_spring_security.sevice.AccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin
public class AccountController {


    private AuthenticationManager authenticationManager;
    private AccountService accountService;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService jwtInMemoryUserDetailsService;

    public AccountController(AuthenticationManager authenticationManager, AccountService accountService, JwtTokenUtil jwtTokenUtil,
                             @Qualifier("jwtUserDetailsService")
                             UserDetailsService jwtInMemoryUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody @Valid LoginRequest loginRequest)
            throws Exception {

        authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(loginRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginRespond(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signin(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Account account = new Account();
            account.setUsername(loginRequest.getUsername());

            // encode password
            account.setPassword(new BCryptPasswordEncoder(5).encode(loginRequest.getPassword()));

            //thêm giả định các trường chưa sử dụng đến
            account.setRole(1);
            account.setMail(loginRequest.getUsername() + "@gmail.com");
            accountService.save(account);
            return ResponseEntity.ok().body("Tạo thành công");
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

