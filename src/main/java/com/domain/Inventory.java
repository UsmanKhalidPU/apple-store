package com.domain;
import com.google.gson.annotations.*;

public class Inventory {

    @SerializedName ("id")
    private Integer id;
    @SerializedName ("item_name")
    private String itemName;

    @SerializedName ("item_quantity")
    private Integer itemQuantity;

    @SerializedName ("item_category_id")
    private String itemCategoryId ;

    @SerializedName ("item_location_id")
    private String itemLocationId;

    @SerializedName("item_category")
    private ItemCategory itemCategory;

    @SerializedName("item_location")
    private ItemLocation itemLocation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(String itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemLocationId() {
        return itemLocationId;
    }

    public void setItemLocationId(String itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ItemLocation getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(ItemLocation itemLocation) {
        this.itemLocation = itemLocation;
    }

}
