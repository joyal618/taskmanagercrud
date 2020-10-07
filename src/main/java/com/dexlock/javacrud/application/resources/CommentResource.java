package com.dexlock.javacrud.application.resources;


import com.dexlock.javacrud.dao.CommentDao;
import com.dexlock.javacrud.db.mongodb.MongoDBConnection;
import com.dexlock.javacrud.models.Comment;
import com.mongodb.client.MongoCollection;
import io.dropwizard.jersey.PATCH;
import org.bson.Document;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/comments")
public class CommentResource {
    CommentDao commentDao = new CommentDao();

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(Comment comment, @QueryParam("taskNo")
                                String taskNo,@HeaderParam("Authorization") String token){
        boolean commentAdded = commentDao.addComment(comment,taskNo,token);
        return Response.ok().build();
    }

    @Path("/edit")
    @PUT
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editComment(Comment comment){
        boolean commentAdded = commentDao.editComment(comment);
        return Response.ok().build();
    }

    @Path("/delete")
    @DELETE
    @RolesAllowed({"SuperAdmin","ProjectManager"})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(@QueryParam("id") String commentId){
        Boolean isDeleted = commentDao.deleteComment(commentId);
        return Response.ok("Success").build();
    }
}
