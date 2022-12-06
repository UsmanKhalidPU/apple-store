package com.services;

import com.domain.Inventory;
import com.domain.ItemCategory;
import com.domain.ItemLocation;
import com.resources.AppleStoreResource;
import junit.framework.TestCase;
import org.mockito.Mock;

public class AppleStoreServiceTest extends TestCase {

    AppleStoreService appleStoreService = new AppleStoreService();
    private Inventory inventory = new Inventory();

    private ItemLocation itemLocation = new ItemLocation();

    private ItemCategory itemCategory =new ItemCategory();

    @Mock
    private Inventory mockInventory = new Inventory();

    public void setInventoryData(){
        itemLocation.setLocationName("Phoenix");
        itemLocation.setId(7);

        itemCategory.setId(24);
        itemCategory.setCategoryName("Phone");

        inventory.setItemName("iPhone X");
        inventory.setItemQuantity(22);
        inventory.setItemLocation(itemLocation);
        inventory.setItemCategory(itemCategory);
    }

    public void testListAll() {
        String data = appleStoreService.listAll();
        String str = "[{\"id\":1,\"item_name\":\"iPhone 13\",\"item_quantity\":10,\"item_category\":{\"id\":24,\"category_name\":\"Phone\"},\"item_location\":{\"id\":7,\"location_name\":\"Phoenix\"}},{\"id\":3,\"item_name\":\"Macbook Air\",\"item_quantity\":15,\"item_category\":{\"id\":22,\"category_name\":\"Laptop\"},\"item_location\":{\"id\":9,\"location_name\":\"Arizona\"}},{\"id\":4,\"item_name\":\"Macbook Pro\",\"item_quantity\":20,\"item_category\":{\"id\":22,\"category_name\":\"Laptop\"},\"item_location\":{\"id\":9,\"location_name\":\"Arizona\"}}]";
        assertEquals(data, str);
    }

    public void testListById() {
        String data = appleStoreService.listById(3);
        String str = "{\"id\":1,\"item_name\":\"iPhone 13\",\"item_quantity\":10,\"item_category\":{\"id\":24,\"category_name\":\"Phone\"},\"item_location\":{\"id\":7,\"location_name\":\"Phoenix\"}}";
        assertEquals(data, str);
    }

    public void testListByCategory() {
        String data = appleStoreService.listByCategory(22);
        String str = "[{\"id\":3,\"item_name\":\"Macbook Air\",\"item_quantity\":15,\"item_category\":{\"id\":22,\"category_name\":\"Laptop\"},\"item_location\":{\"id\":9,\"location_name\":\"Arizona\"}},{\"id\":4,\"item_name\":\"Macbook Pro\",\"item_quantity\":20,\"item_category\":{\"id\":22,\"category_name\":\"Laptop\"},\"item_location\":{\"id\":9,\"location_name\":\"Arizona\"}}]";
        assertEquals(data, str);
    }

    public void testListByLocation() {
        String data = appleStoreService.listByLocation(7);
        String str = "[{\"id\":1,\"item_name\":\"iPhone 13\",\"item_quantity\":10,\"item_category\":{\"id\":24,\"category_name\":\"Phone\"},\"item_location\":{\"id\":7,\"location_name\":\"Phoenix\"}}]";
        assertEquals(data, str);

    }

    public void testListByCatLoc() {
        String data = appleStoreService.listByCatLoc(22,9);
//        String str = "[{\"id\":3,\"item_name\":\"Macbook Air\",\"item_quantity\":15,\"item_category\":{\"id\":22,\"category_name\":\"Laptop\"},\"item_location\":{\"id\":9,\"location_name\":\"Arizona\"}},{\"id\":4,\"item_name\":\"Macbook Pro\",\"item_quantity\":20,\"item_category\":{\"id\":22,\"category_name\":\"Laptop\"},\"item_location\":{\"id\":9,\"location_name\":\"Arizona\"}}]";
        String str = "[{\"id\":3,\"item_name\":\"Macbook Air\",\"item_quantity\":15,\"item_category\":{\"id\":22,\"category_name\":\"Laptop\"},\"item_location\":{\"id\":9,\"location_name\":\"Arizona\"}},{\"id\":4,\"item_name\":\"Macbook Pro\",\"item_quantity\":20,\"item_category\":{\"id\":22,\"category_name\":\"Laptop\"},\"item_location\":{\"id\":9,\"location_name\":\"Arizona\"}}]";
        assertEquals(data, str);
    }

    public void testAddItem() {
        setInventoryData();
        appleStoreService.addItem(inventory);
        appleStoreService.addItem(mockInventory);
    }

    public void testUpdateItem() {
        setInventoryData();
        appleStoreService.updateItem(inventory, 2);
        appleStoreService.updateItem(inventory, 33);
        appleStoreService.updateItem(mockInventory,2);
    }

    public void testDeleteItem() {
        appleStoreService.deleteItem(14);
        appleStoreService.deleteItem(20);
    }

    public void testAuthUser() {
        String str = "Basic YWRtaW46cm9vdA==";
        appleStoreService.authUser(str);
    }
}