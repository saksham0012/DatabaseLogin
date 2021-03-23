package com.example.loginregistrationdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregistrationdatabase.model.pojo.ImagePojo;

import java.util.List;

import retrofit2.Call;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<ImageServices> images;
    LayoutInflater inflater;

    public Adapter(Context ctx, List<ImageServices> images){
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.gridIcon.setImageResource(images.get(position));
        ImageServices imageServices=images.get(position);
        Call<List<ImagePojo>> name=imageServices.getImages();

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            gridIcon = itemView.findViewById(R.id.imageView);
        }
    }
}
