package com.example.loginregistrationdatabase.controller;

import android.provider.SyncStateContract;

import com.example.loginregistrationdatabase.ImageServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestManager {

    private ImageServices mImageService;

        public ImageServices getFlowerService() {
            if (mImageService == null) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://gist.githubusercontent.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                mImageService = retrofit.create(ImageServices.class);
            }
            return mImageService;
        }
    }

