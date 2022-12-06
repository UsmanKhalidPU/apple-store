package com.domain;

import com.google.gson.annotations.SerializedName;

public class ItemLocation {

    @SerializedName("id")
    private Integer id;
    @SerializedName("location_name")
    private String locationName;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
