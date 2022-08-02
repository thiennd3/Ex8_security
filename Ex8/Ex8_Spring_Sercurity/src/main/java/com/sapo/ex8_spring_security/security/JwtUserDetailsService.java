package com.sapo.ex8_spring_security.security;

import com.sapo.ex8_spring_security.model.entity.Account;
import com.sapo.ex8_spring_security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Account account = userRepository.getUserByUsername(username);

        if (account != null) {
            return new User(account.getUsername(), account.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Not found: " + username);
        }
    }
}
