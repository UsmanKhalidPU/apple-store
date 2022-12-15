package com.resources;

import junit.framework.TestCase;

public class AppleStoreResourceTest extends TestCase {

    AppleStoreResource appleStoreResource = new AppleStoreResource();
    String str = "{\"item_name\":\"iPhone 12\",\"item_quantity\":10,\"item_category\":{\"id\":24,\"category_name\":\"Phone\"},\"item_location\":{\"id\":7,\"location_name\":\"Phoenix\"}}";

    public void testListAll() {
//        appleStoreResource.listAll("Basic YWRtaW46cm9vdA==");
//        appleStoreResource.listAll("Basic YWRtaW46cm9Adv==");
    }

    public void testListById() {
        appleStoreResource.listById(2,"Basic YWRtaW46cm9vdA==");
        appleStoreResource.listById(2,"Basic YWRtaW46cm9Adv==");
    }

    public void testListByCategory() {
        appleStoreResource.listByCategory(24, "Basic YWRtaW46cm9vdA==");
        appleStoreResource.listByCategory(24, "Basic YWRtaW46cm9Adv==");
    }

    public void testListByLocation() {
        appleStoreResource.listByLocation(9,"Basic YWRtaW46cm9vdA==");
        appleStoreResource.listByLocation(9,"Basic YWRtaW46cm9Adv==");
    }

    public void testListByCatLoc() {
        appleStoreResource.listByCatLoc(25,9, "Basic YWRtaW46cm9vdA==");
        appleStoreResource.listByCatLoc(25,9, "Basic YWRtaW46cm9Adv==");
    }

    public void testAddItem() {
        appleStoreResource.addItem(str,"Basic YWRtaW46cm9vdA==");
        appleStoreResource.addItem(str,"Basic YWRtaW46cm9Adv==");
    }

    public void testUpdateItem() {
        appleStoreResource.updateItem(2, str,"Basic YWRtaW46cm9vdA==");
        appleStoreResource.updateItem(2, str,"Basic YWRtaW46cm9Adv==");
    }

    public void testDeleteItem() {
        appleStoreResource.deleteItem(8,"Basic YWRtaW46cm9vdA==");
        appleStoreResource.deleteItem(8,"Basic YWRtaW46cm9Adv==");
    }
}