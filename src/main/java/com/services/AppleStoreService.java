package com.services;

import com.domain.Inventory;
import com.domain.ItemCategory;
import com.domain.ItemLocation;
import com.google.gson.Gson;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.security.MessageDigest;

public class AppleStoreService {

    public String listAll() {
        Connection con = null;
        Statement stmt;

        try {
            con = DBUtilService.getDataSource().getConnection();
            stmt = con.createStatement();
            String SQL = "select * from inventory, item_category, item_location where " +
                    "inventory.item_category_id = item_category.id AND inventory.item_location_id = item_location.id;";
            ResultSet rs = stmt.executeQuery(SQL);
            List<Inventory> inventoryItems = new ArrayList<Inventory>();

            while (rs.next()) {
                inventoryItems.add(resultSetToInventory(rs));
            }
//            System.out.println(new Gson().toJson(inventoryItems));
            return new Gson().toJson(inventoryItems);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Returned");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return "Please check your configurations";
    }

    public String listById(Integer inventoryId) {
        Connection con = null;
        Statement stmt;

        try {
            con = DBUtilService.getDataSource().getConnection();
            stmt = con.createStatement();
            String SQL = "select * from inventory, item_category, item_location where inventory.id = '" + inventoryId +
                    "' AND inventory.item_category_id = item_category.id AND inventory.item_location_id = item_location.id;";
            ResultSet rs = stmt.executeQuery(SQL);
            Inventory inventoryItem = new Inventory();

            if (rs.next()) {
                inventoryItem = resultSetToInventory(rs);
            }
            return new Gson().toJson(inventoryItem);
        } catch (Exception e) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Returned");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return "Please check your configurations";
    }

    public String listByCategory(Integer categoryId) {
        Connection con = null;
        Statement stmtScroll;

        try {
            con = DBUtilService.getDataSource().getConnection();
            stmtScroll = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "select * from inventory, item_category, item_location where inventory.item_category_id = " + categoryId +
                    " AND inventory.item_category_id = item_category.id AND inventory.item_location_id = item_location.id;";
            ResultSet rs = stmtScroll.executeQuery(SQL);
            List<Inventory> inventoryItems = new ArrayList<Inventory>();

            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    inventoryItems.add(resultSetToInventory(rs));
                }
                return new Gson().toJson(inventoryItems);
            } else {
                return "No inventory left";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Returned");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return "Please check your configurations";
    }

    public String listByLocation(Integer locationId) {
        Connection con = null;
        Statement stmtScroll;

        try {
            con = DBUtilService.getDataSource().getConnection();
            stmtScroll = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "select * from inventory, item_category, item_location where inventory.item_location_id = " + locationId +
                    " AND inventory.item_category_id = item_category.id AND inventory.item_location_id = item_location.id;";
            ResultSet rs = stmtScroll.executeQuery(SQL);
            List<Inventory> inventoryItems = new ArrayList<Inventory>();

            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    inventoryItems.add(resultSetToInventory(rs));
                }
                return new Gson().toJson(inventoryItems);
            } else {
                return "No inventory left";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Returned");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return "Please check your configurations";
    }

    public String listByCatLoc(Integer categoryId, Integer locationId) {
        Connection con = null;
        Statement stmtScroll;

        try {
            con = DBUtilService.getDataSource().getConnection();
            stmtScroll = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "select * from inventory, item_category, item_location where inventory.item_category_id = " + categoryId +
                    " AND inventory.item_location_id = " + locationId + " AND inventory.item_category_id = item_category.id AND " +
                    "inventory.item_location_id = item_location.id;";
            ResultSet rs = stmtScroll.executeQuery(SQL);
            List<Inventory> inventoryItems = new ArrayList<Inventory>();

            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    inventoryItems.add(resultSetToInventory(rs));
                }
                return new Gson().toJson(inventoryItems);
            } else {
                return "No inventory left";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Returned");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return "Please check your configurations";
    }

    public void addItem(Inventory inventory) {
        Connection con = null;

        try {
            con = DBUtilService.getDataSource().getConnection();
            String SQL = "insert into inventory (item_name, item_quantity, item_category_id, item_location_id) values (?,?,?,?); ";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, inventory.getItemName());
            pstmt.setInt(2, inventory.getItemQuantity());
            pstmt.setInt(3, inventory.getItemCategory().getId());
            pstmt.setInt(4, inventory.getItemLocation().getId());
            int record = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Returned");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public String updateItem(Inventory inventory, int id) {
        Connection con = null;
        Statement stmt;

        try {
            con = DBUtilService.getDataSource().getConnection();
            stmt = con.createStatement();
            String SQL = "select * from inventory where inventory.id='" + id + "'";
            ResultSet rs;
            rs = stmt.executeQuery(SQL);

            if (rs.next()) {
                String sqlUpdate = "update inventory set item_name = '" + inventory.getItemName() + "', " + "item_quantity = '" + inventory.getItemQuantity() + "' where inventory.id = '" + id + "'";
                System.out.println(sqlUpdate);
                int records = stmt.executeUpdate(sqlUpdate);
                System.out.println("No. of records updated: " + records);
                inventory.setId(id);
                return new Gson().toJson(inventory);
            } else {
                System.out.println("invalid key");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Returned");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return "Please check your logic";
    }

    public String deleteItem(int id) {
        Connection con = null;
        Statement stmt;
        try {
            con = DBUtilService.getDataSource().getConnection();
            stmt = con.createStatement();
            String SQL = "select * from inventory where inventory.id='" + id + "'";
            ResultSet rs;
            rs = stmt.executeQuery(SQL);

            if (rs.next()) {
                String sqlUpdate = "delete from inventory where inventory.id='" + id + "'";
                System.out.println(sqlUpdate);
                int records = stmt.executeUpdate(sqlUpdate);
                System.out.println("No. of records deleted: " + records);
                return "{\"message\" : \"OK\"}";
            } else {
                return ("invalid key");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Returned");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return "Please check your logic";
    }

    public Boolean authUser(String auth) {
        String authStr = auth;
        String[] authParts = authStr.split(" ");
        authStr = authParts[1];
        byte[] decoded = Base64.getDecoder().decode(authStr);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        authParts = decodedStr.split(":");

        Connection con = null;
        Statement stmt;
        try {
            con = DBUtilService.getDataSource().getConnection();
            stmt = con.createStatement();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(authParts[1].getBytes(), 0, authParts[1].length());
//            System.out.println("MD5: "+ new BigInteger(1,md.digest()).toString(16));
//            String result = new String();
//            result = new BigInteger(1,md.digest()).toString(16);
//            System.out.println(result);
//            String SQL = "select * from users where users.name = '" + authParts[0] + "' AND users.password = '" + result +"';" ;
            String SQL = "select * from users where users.name = '" + authParts[0] + "' AND " +
                    "users.password = '" + new BigInteger(1, md.digest()).toString(16) + "';";
            ResultSet rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection Returned");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return false;
    }

    private Inventory resultSetToInventory(ResultSet rs) throws SQLException {
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

        return inventoryItem;
    }
}