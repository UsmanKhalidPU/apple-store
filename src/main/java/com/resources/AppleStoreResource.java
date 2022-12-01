package com.resources;

import com.domain.Inventory;
import com.google.gson.Gson;
import com.services.AppleStoreService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/inventory")
public class AppleStoreResource {

    AppleStoreService appleStoreService = new AppleStoreService();

    @GET
    @Path("/list")
    public Response listAll()
    {
        String str = appleStoreService.listAll();
        return Response.ok(str).build();
    }

    @GET
    @Path("/{inventory_id}")
    public Response listById(@PathParam("inventory_id")Integer id)
    {
        String str = appleStoreService.listById(id);
        return Response.ok(str).build();
    }
    @GET
    @Path("/listByCategory")
    public Response listByCategory(@QueryParam("category") Integer categoryId)
    {
        String str = appleStoreService.listByCategory(categoryId);
        return Response.ok(str).build();
    }

    @GET
    @Path("/listByLocation")
    public Response listByLocation(@QueryParam("location") Integer locationId)
    {
        String str = appleStoreService.listByLocation(locationId);
        return Response.ok(str).build();
    }

    @GET
    @Path("/listByCatLoc")
    public Response listByCatLoc(@QueryParam ("category") Integer categoryId, @QueryParam("location") Integer locationId)
    {
        String str = appleStoreService.listByCatLoc(categoryId, locationId);
        return Response.ok(str).build();
    }

    @POST
    @Path("/add")
    public Response addItem(String str)
    {
        Gson gson = new Gson();
        Inventory inventory = gson.fromJson(str, Inventory.class);
        appleStoreService.addItem(inventory);
        return Response.ok(str).build();
    }

}
