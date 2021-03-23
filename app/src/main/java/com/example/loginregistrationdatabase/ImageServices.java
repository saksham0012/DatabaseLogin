package com.example.loginregistrationdatabase;

import com.example.loginregistrationdatabase.model.pojo.ImagePojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageServices {


    @GET("Amar2601/9a5eba5aa8c40881faa1e95265128624/raw/047ab382cb0ee69d16178783010ea054488fa39f/image_mata_data.json")
    Call<List<ImagePojo>> getImages();
}
