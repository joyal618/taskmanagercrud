package com.dexlock.javacrud.application;

import com.dexlock.javacrud.dao.UserDao;
import com.dexlock.javacrud.models.User;
import com.mongodb.client.MongoCollection;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserAuthenticator implements Authenticator<String, User> {
    UserDao userDao = new UserDao();
    @Override
        public Optional<User> authenticate(String token) throws AuthenticationException {
            if (userDao.getActiveUsers(token)) {
                return Optional.of(userDao.getUser(token));
            }
            return Optional.empty();
        }
    }


