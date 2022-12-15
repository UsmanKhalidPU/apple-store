package com.resources;

import com.domain.Inventory;
import com.google.gson.Gson;
import com.services.AppleStoreService;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.lang.annotation.*;

@Path("/inventory")
public class AppleStoreResource {

    AppleStoreService appleStoreService = new AppleStoreService();

    @MyAuth
    @GET
    @Path("myAuth/list")
    public Response myAuthListAll() {
        return Response.ok(appleStoreService.listAll()).build();
    }

    @GET
    @Path("/requestFilter/list")
    public Response crListAll(ContainerRequestContext auth)  {
        if(auth.getHeaders().getFirst("Authorization").equals("Basic YWRtaW46cm9vdA==")) {
            return Response.ok(appleStoreService.listAll()).build();
        }
        {return Response.ok().build();}
    }

    @GET
    @Path("/list")
    public Response listAll(@HeaderParam("authorization") String auth) {
        if (appleStoreService.authUser(auth)) {
            return Response.ok(appleStoreService.listAll()).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/{inventory_id}")
    public Response listById(@PathParam("inventory_id") Integer id, @HeaderParam("authorization") String auth) {
        if (appleStoreService.authUser(auth)) {
            return Response.ok(appleStoreService.listById(id)).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/listByCategory")
    public Response listByCategory(@QueryParam("category") Integer categoryId, @HeaderParam("authorization") String auth) {
        if (appleStoreService.authUser(auth)) {
            return Response.ok(appleStoreService.listByCategory(categoryId)).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/listByLocation")
    public Response listByLocation(@QueryParam("location") Integer locationId, @HeaderParam("authorization") String auth) {
        if (appleStoreService.authUser(auth)) {
            return Response.ok(appleStoreService.listByLocation(locationId)).build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/listByCatLoc")
    public Response listByCatLoc(@QueryParam("category") Integer categoryId, @QueryParam("location") Integer locationId, @HeaderParam("authorization") String auth) {
        if (appleStoreService.authUser(auth)) {
            return Response.ok(appleStoreService.listByCatLoc(categoryId, locationId)).build();
        } else {
            return Response.ok().build();
        }
    }

    @POST
    @Path("/add")
    public Response addItem(String str, @HeaderParam("authorization") String auth) {
        if (appleStoreService.authUser(auth)) {
            appleStoreService.addItem(new Gson().fromJson(str, Inventory.class));
            return Response.ok(str).build();
        } else {
            return Response.ok().build();
        }
    }

    @PUT
    @Path("/{inventory_id}")
    public Response updateItem(@PathParam("inventory_id") Integer id, String str, @HeaderParam("authorization") String auth) {
        if (appleStoreService.authUser(auth)) {
            String updatedStr = appleStoreService.updateItem(new Gson().fromJson(str, Inventory.class), id);
            return Response.ok(updatedStr).build();
        } else {
            return Response.ok().build();
        }
    }

    @DELETE
    @Path("/{inventory_id}")
    public Response deleteItem(@PathParam("inventory_id") Integer id, @HeaderParam("authorization") String auth) {
        if (appleStoreService.authUser(auth)) {
            return Response.ok(appleStoreService.deleteItem(id)).build();
        } else {
            return Response.ok().build();
        }
    }
}