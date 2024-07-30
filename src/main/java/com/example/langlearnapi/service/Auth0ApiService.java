package com.example.langlearnapi.service;

import com.example.langlearnapi.dto.Auth0ApiUserUpdateRequestDto;
import com.example.langlearnapi.model.Auth0User;

public interface Auth0ApiService {
    Auth0User getAuth0UserFromApi(String userId);
    Auth0User updateAuth0UserFromApi(Auth0ApiUserUpdateRequestDto updateRequestDto, String userId);
}
