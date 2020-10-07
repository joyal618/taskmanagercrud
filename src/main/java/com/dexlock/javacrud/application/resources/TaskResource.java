package com.dexlock.javacrud.application.resources;

import com.dexlock.javacrud.dao.TaskDao;
import com.dexlock.javacrud.models.Task;
//import com.sun.xml.internal.ws.api.model.MEP;
import io.dropwizard.jersey.PATCH;
import org.bson.Document;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

@Path("/task")
public class TaskResource {
    TaskDao daoTask = new TaskDao();
    public TaskResource() {
    }
    @Path("/view")
    @GET
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getTask(@QueryParam("page") String pageNo){
        List<Document> tasks = daoTask.getTasks(pageNo);
        return Response.ok(tasks).build();
    }

    @Path("/add")
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTask(Task taskToBeAdded,@HeaderParam("Authorization") String token){
        Document taskAdded = daoTask.addTask(taskToBeAdded,token);
        return Response.ok(taskAdded).build();
    }

    @Path("/add-attachment")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAttachment(@FormDataParam("file") InputStream fileInputStream,
                                  @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                  @FormDataParam("taskNo") String taskNo

    ) throws IOException {
        String UPLOAD_PATH = "/home/dxuser/";

            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
            while ((read = fileInputStream.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();

        Boolean fileAttached = daoTask.attachFile(taskNo,UPLOAD_PATH+fileMetaData.getFileName());
        return Response.ok("Data uploaded successfully !!").build();

    }

    @Path("/update")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTask(Task taskToBeUpdated){
        Document taskUpdated = daoTask.updateTask(taskToBeUpdated);
        return Response.ok().build();
    }
    @Path("/delete-file")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  Response deleteFile(@QueryParam("fileName") String filename){
        String deletionFileLocation = "/home/dxuser/" + filename;
        return Response.ok(daoTask.deleteFile(filename,deletionFileLocation)).build();
    }
    @Path("/download-file")
    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@QueryParam("fileName") String fileName){
        String downloadFileLocation = "/home/dxuser/" + fileName;
        return Response.ok(daoTask.downloadFile(downloadFileLocation)).build();
    }
    @Path("/delete")
    @GET
    public Response deleteTask(){
        return Response.ok().build();
    }


}
