package com.example.langlearnapi.dao;

import com.example.langlearnapi.model.Auth0User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Auth0UserRepository extends JpaRepository<Auth0User, String> {
}