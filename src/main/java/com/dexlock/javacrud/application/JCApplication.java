package com.dexlock.javacrud.application;

import com.dexlock.javacrud.application.resources.CommentResource;
import com.dexlock.javacrud.application.resources.TaskResource;
import com.dexlock.javacrud.application.resources.UserResource;
import com.dexlock.javacrud.models.User;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;


public class JCApplication extends Application<ApplicationConfig>  {

    public void run(ApplicationConfig applicationConfig, Environment environment) throws Exception {
        environment.jersey().register(MultiPartFeature.class);
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(new UserResource());
        environment.jersey().register(new TaskResource());
        environment.jersey().register(new CommentResource());
        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new UserAuthenticator())
                        .setAuthorizer(new UserAuthorizer())
                        .setPrefix("Bearer")
                        .buildAuthFilter()));

    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfig> bootstrap) {
        super.initialize(bootstrap);
    }

    public static void main(String[] args) throws Exception {
        new JCApplication().run(args);

    }

}
