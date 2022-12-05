package com.resources;

import junit.framework.TestCase;

import javax.ws.rs.HeaderParam;

public class AppleStoreResourceTest extends TestCase {

    AppleStoreResource appleStoreResource = new AppleStoreResource();

    public void testListAll() {
        appleStoreResource.listAll("garbage admin:63a9f0ea7bb98050796b649e85481845");
    }

    public void testListById() {
    }

    public void testListByCategory() {
    }

    public void testListByLocation() {
    }

    public void testListByCatLoc() {
    }

    public void testAddItem() {
    }

    public void testUpdateItem() {
    }

    public void testDeleteItem() {
    }
}