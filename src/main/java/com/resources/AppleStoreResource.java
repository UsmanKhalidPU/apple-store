package com.resources;

import com.services.AppleStoreService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/inventory")
public class AppleStoreResource {

    AppleStoreService appleStoreService = new AppleStoreService();

    @GET
    @Path("/list")
    public Response listAll()
    {
        String str = appleStoreService.fetchAll();
        return Response.ok(str).build();
    }

    @GET
    @Path("/{inventory_id}")
    public Response listAll(@PathParam("inventory_id")Integer id)
    {
        String str = appleStoreService.fetchById(id);
        return Response.ok(str).build();
    }
    @GET
    @Path("/listByCategory")
    public Response fetchByCategory(@QueryParam("category") Integer catId)
    {
        String str = appleStoreService.fetchByCategory(catId);
        return Response.ok(str).build();
    }
}
