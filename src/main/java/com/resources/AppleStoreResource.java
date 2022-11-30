package com.resources;

import com.services.AppleStoreService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/inventory")
public class AppleStoreResource {

    @GET
    @Path("/list")
    public Response listAll()
    {
        AppleStoreService li = new AppleStoreService();
        String str = li.list();
        return Response.ok(str).build();
    }
}
