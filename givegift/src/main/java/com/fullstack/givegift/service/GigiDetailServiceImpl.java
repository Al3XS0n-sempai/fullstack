package com.fullstack.givegift.service;

import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.repository.GigiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GigiDetailServiceImpl implements UserDetailsService {
    @Autowired
    private GigiRepository gigiRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Gigi> gigiOpt = gigiRepository.findByUsername(username);
        return gigiOpt.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
