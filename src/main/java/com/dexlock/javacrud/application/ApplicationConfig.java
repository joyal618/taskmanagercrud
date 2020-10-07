package com.dexlock.javacrud.application;

import io.dropwizard.Configuration;
import javax.validation.constraints.NotNull;

public class ApplicationConfig extends Configuration {
    @NotNull
    private String mongoHost;
    @NotNull
    private int mongoPort;
    @NotNull
    private String mongoDB;

    public String getMongoHost() {
        return mongoHost;
    }

    public int getMongoPort() {
        return mongoPort;
    }

    public String getMongoDB() {
        return mongoDB;
    }
}

