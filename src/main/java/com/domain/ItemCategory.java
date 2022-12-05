package com.domain;

import com.google.gson.annotations.SerializedName;

public class ItemCategory {

   @SerializedName("id")
    private Integer id;

    @SerializedName("category_name")
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() { return categoryName;}

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
