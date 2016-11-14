
package com.example.antonio.testapp;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSON {

    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

}
