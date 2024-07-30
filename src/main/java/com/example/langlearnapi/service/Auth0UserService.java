package com.example.langlearnapi.service;

import com.example.langlearnapi.model.Auth0User;

public interface Auth0UserService {
    Auth0User getAuth0UserById(String id);
    Auth0User addNewAuth0User(Auth0User auth0User);
}
