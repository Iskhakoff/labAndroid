package com.example.machine_time.lab4.net.Model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class Character implements Serializable{

    @SerializedName("name")
    private String name;
    @SerializedName("thumbnail")
    private Image thumbnail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", thumbnail =" + thumbnail +
                '}';
    }
}
