package com.example.loginregistrationdatabase.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImagePojo {

    @SerializedName("images")
    private ArrayList<ImageModel> imageList;

    public ArrayList<ImageModel> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<ImageModel> imageList) {
        this.imageList = imageList;
    }
}

