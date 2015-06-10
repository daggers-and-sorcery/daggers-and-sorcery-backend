package com.swordssorcery.server.model.repository;

import com.swordssorcery.server.model.User;

public interface CustomUserRepository {
    public User queryByNameAndPass(String username, String password);
}