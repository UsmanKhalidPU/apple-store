package com.services;

import com.domain.Inventory;
import com.domain.ItemCategory;
import com.domain.ItemLocation;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppleStoreService {
    public String list()
    {
        Connection con = null;
        Statement stmt;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/applestore", "root", "root");
            stmt = con.createStatement();

//            String SQL = "select * from applestore.inventory as AI, applestore.item_category as AC, applestore.item_location as AL where AI.item_category_id = AC.id AND AI.item_location_id = AL.id";

            String SQL = "select * from inventory, item_category, item_location where inventory.item_category_id = item_category.id AND inventory.item_location_id = item_location.id;";
            System.out.println(SQL);
            ResultSet rs = stmt.executeQuery(SQL);

            List<Inventory> inventoryItems = new ArrayList<Inventory>();

            while(rs.next()){
                Inventory inventoryItem = new Inventory();
                ItemCategory itemCategory = new ItemCategory();
                ItemLocation itemLocation = new ItemLocation();

                inventoryItem.setId(rs.getInt("inventory.id"));
                inventoryItem.setItemName(rs.getString("inventory.item_name"));
                inventoryItem.setItemQuantity(rs.getInt("inventory.item_quantity"));

                itemCategory.setId(rs.getInt("item_category.id"));
                itemCategory.setCategoryName(rs.getString("item_category.category_name"));

                itemLocation.setId(rs.getInt("item_location.id"));
                itemLocation.setLocationName(rs.getString("item_location.location_name"));

                inventoryItem.setItemCategory(itemCategory);
                inventoryItem.setItemLocation(itemLocation);

                inventoryItems.add(inventoryItem);
            }

            Gson gson = new Gson();
            String json = gson.toJson(inventoryItems);
            System.out.println(json);
            return json;

        }

        catch (Exception e) {
            System.out.println(e);
        }
        
        finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Closed");
                } 
                catch (SQLException e) { 
                    System.out.println(e);
                }
            }
        }
        return "No inventory left";
    }
}