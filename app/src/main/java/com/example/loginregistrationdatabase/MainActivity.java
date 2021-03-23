package com.example.loginregistrationdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.loginregistrationdatabase.model.pojo.ImagePojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView dataList;
    List<Integer> images;
    Adapter adapter;
    private DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = findViewById(R.id.dataList);

        images= new ArrayList<>();

        images.add(R.drawable.ic_launcher_background);
        images.add(R.drawable.ic_launcher_background);
        images.add(R.drawable.ic_launcher_background);
        images.add(R.drawable.ic_launcher_background);
        images.add(R.drawable.ic_launcher_background);

        adapter = new Adapter(this,images);
        myDB = new DataBaseHelper(this);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageServices imageServices=retrofit.create(ImageServices.class);

        Call<List<ImagePojo>> call = imageServices.getImages();
        call.enqueue(new Callback<List<ImagePojo>>() {
            @Override
            public void onResponse(Call<List<ImagePojo>> call, Response<List<ImagePojo>> response) {
                List<ImagePojo> image = response.body();

                for(ImagePojo imageServices1:image){
                    String content= "";
                    boolean var = myDB.ImageApiRecord( imageServices1.getImageList().get(0).getAlbumId(),
                    imageServices1.getImageList().get(0).getId(),
                    imageServices1.getImageList().get(0).getTitle(),
                    imageServices1.getImageList().get(0).getUrl(),
                            imageServices1.getImageList().get(0).getThumbnailUrl());
                    if(var){
                        Toast.makeText(MainActivity.this, "API Data Registered Successfully !!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Error in Putting Data in DB!!", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<ImagePojo>> call, Throwable t) {

            }
        });


    }
}