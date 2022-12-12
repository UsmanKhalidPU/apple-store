package com.resources;

import com.domain.Inventory;
import com.domain.MyAuth;
import com.google.gson.Gson;
import com.services.AppleStoreService;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

@Path("/inventory")
public class AppleStoreResource {

    AppleStoreService appleStoreService = new AppleStoreService();
    //public @interface Authorization{}

    @GET
    @Path("myAuth/list")
    @MyAuth
    public Response myAuthListAll() {
        return Response.ok("Authenticated").build();
//        System.out.println(auth);
//        if(appleStoreService.authUser(auth)) {
//            String str = appleStoreService.listAll();
//            return Response.ok(str).build();
//        }
//        else {return Response.ok().build();}
    }
    
    @GET
    @Path("/list")
    public Response listAll(@HeaderParam("authorization") String auth) {
        if(appleStoreService.authUser(auth)) {
            String str = appleStoreService.listAll();
            return Response.ok(str).build();
            }
        else {return Response.ok().build();}
    }

    @GET
    @Path("/{inventory_id}")
    public Response listById(@PathParam("inventory_id")Integer id, @HeaderParam("authorization") String auth) {
        if(appleStoreService.authUser(auth)) {
            String str = appleStoreService.listById(id);
            return Response.ok(str).build();
        }
        else {return Response.ok().build();}
    }

    @GET
    @Path("/listByCategory")
    public Response listByCategory(@QueryParam("category") Integer categoryId, @HeaderParam("authorization") String auth) {
        if(appleStoreService.authUser(auth)) {
            String str = appleStoreService.listByCategory(categoryId);
            return Response.ok(str).build();
        }
        else {return Response.ok().build();}
    }

    @GET
    @Path("/listByLocation")
    public Response listByLocation(@QueryParam("location") Integer locationId, @HeaderParam("authorization") String auth) {
        if(appleStoreService.authUser(auth)) {
            String str = appleStoreService.listByLocation(locationId);
            return Response.ok(str).build();
        }
        else {return Response.ok().build();}
    }

    @GET
    @Path("/listByCatLoc")
    public Response listByCatLoc(@QueryParam ("category") Integer categoryId, @QueryParam("location") Integer locationId, @HeaderParam("authorization") String auth) {
        if(appleStoreService.authUser(auth)) {
            String str = appleStoreService.listByCatLoc(categoryId, locationId);
            return Response.ok(str).build();
        }
        else {return Response.ok().build();}
    }

    @POST
    @Path("/add")
    public Response addItem(String str, @HeaderParam("authorization") String auth) {
        if(appleStoreService.authUser(auth)) {
            Gson gson = new Gson();
            Inventory inventory = gson.fromJson(str, Inventory.class);
            appleStoreService.addItem(inventory);
            return Response.ok(str).build();
        }
        else {return Response.ok().build();}
    }

    @PUT
    @Path("/{inventory_id}")
    public Response updateItem(@PathParam("inventory_id")Integer id, String str, @HeaderParam("authorization") String auth) {
        if(appleStoreService.authUser(auth)) {
            Gson gson = new Gson();
            Inventory inventory = gson.fromJson(str, Inventory.class);
            String updatedStr = appleStoreService.updateItem(inventory, id);
            return Response.ok(updatedStr).build();
        }
        else {return Response.ok().build();}
    }

    @DELETE
    @Path("/{inventory_id}")
    public Response deleteItem(@PathParam("inventory_id")Integer id, @HeaderParam("authorization") String auth) {
        if(appleStoreService.authUser(auth)) {
            String Str = appleStoreService.deleteItem(id);
            return Response.ok(Str).build();
        }
        else {return Response.ok().build();}
    }

}
//context request container