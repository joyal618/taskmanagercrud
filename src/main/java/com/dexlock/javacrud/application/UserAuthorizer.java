package com.dexlock.javacrud.application;

import com.dexlock.javacrud.models.User;
import io.dropwizard.auth.Authorizer;

public class UserAuthorizer implements Authorizer<User> {

    @Override
        public boolean authorize(User user, String role) {
            String roles = user.getUserRole().toString();
            return role.equals(roles);
        }
    }

