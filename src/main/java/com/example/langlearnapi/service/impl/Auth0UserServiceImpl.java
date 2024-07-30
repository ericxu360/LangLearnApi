package com.example.langlearnapi.service.impl;

import com.example.langlearnapi.dao.Auth0UserRepository;
import com.example.langlearnapi.model.Auth0User;
import com.example.langlearnapi.service.Auth0UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Auth0UserServiceImpl implements Auth0UserService {
    private final Auth0UserRepository auth0UserRepository;

    @Autowired
    public Auth0UserServiceImpl(Auth0UserRepository auth0UserRepository) {
        this.auth0UserRepository = auth0UserRepository;
    }

    @Override
    public Auth0User getAuth0UserById(String id) {
        return auth0UserRepository.findById(id).orElse(null);
    }

    @Override
    public Auth0User addNewAuth0User(Auth0User auth0User) {
        return auth0UserRepository.save(auth0User);
    }
}
