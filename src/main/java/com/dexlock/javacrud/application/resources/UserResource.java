package com.dexlock.javacrud.application.resources;

import com.dexlock.javacrud.dao.UserDao;
import com.dexlock.javacrud.models.User;

import org.bson.Document;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/user")
public class UserResource {
    UserDao userDao = new UserDao();
    public UserResource() {
    }

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers( ) {
        List<Document> allUsers = userDao.getUsers();
        return Response.ok(allUsers).build();
    }



    @Path("/add")
    @POST
    @RolesAllowed("SuperAdmin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User userToBeAdded) {
        Document userAdded = userDao.addUser(userToBeAdded);
        return Response.ok("Success").build();

    }

    @Path("/delete")
    @DELETE
    @RolesAllowed("SuperAdmin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@QueryParam("deleteByName") String deleteByName) {
        String deleted = userDao.deleteUser(deleteByName);
        return Response.ok(deleted).build();

    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(@QueryParam("email") String email,
                              @QueryParam("password") String password){
        System.out.println("hello");
        Document userExists = userDao.loginUser(email,password);
        if(userExists!=null) {
            String userRole = userExists.get("userRole").toString();
            UUID tokenId = UUID.randomUUID();
            String token = tokenId.toString();
            Boolean addActiveUser = userDao.addActiveUsers(token,email,userRole);
            if (addActiveUser){
                return Response.ok().build();
            }
            else{
                return Response.noContent().build();
            }
        }
        else
            return Response.noContent().build();
    }

    @Path("/logout")
    @POST
    @PermitAll
    public Response userLogout(@HeaderParam("Authorization") String token){
        Boolean loggedOut = userDao.logout(token);
        if(loggedOut)
            return Response.ok("Deleted").build();
        else
            return Response.noContent().build();
    }
}

