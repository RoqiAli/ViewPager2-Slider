package com.devcreature.viewpager2slider.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.devcreature.viewpager2slider.R;
import com.devcreature.viewpager2slider.model.EvolveModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.SliderViewHolder> {

    private ArrayList<EvolveModel> evolveModels;
    private ViewPager2 viewPager2;
    private OnSlideListener onSlideListener;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            evolveModels.addAll(evolveModels);
            notifyDataSetChanged();
        }
    };

    public MainAdapter(ArrayList<EvolveModel> evolveModels, ViewPager2 viewPager2, OnSlideListener onSlideListener) {
        this.evolveModels = evolveModels;
        this.viewPager2 = viewPager2;
        this.onSlideListener = onSlideListener;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false), onSlideListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setSliderData(evolveModels.get(position));
        if (position == evolveModels.size() - 1) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return evolveModels.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivPhoto;
        private TextView tvName;
        private TextView tvDesc;
        private OnSlideListener onSlideListener;

        SliderViewHolder(@NonNull View itemView, OnSlideListener onSlideListener) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.main_image);
            tvName = itemView.findViewById(R.id.name_pokemon);
            tvDesc = itemView.findViewById(R.id.desc_pokemon);
            this.onSlideListener = onSlideListener;
            itemView.setOnClickListener(this);
        }

        void setSliderData(EvolveModel evolveModel) {
            ivPhoto.setImageResource(evolveModel.getPhoto());
            tvName.setText(evolveModel.getName());
            tvDesc.setText(evolveModel.getDesc());
        }

        @Override
        public void onClick(View v) {
            onSlideListener.onSlideClickListener(getAdapterPosition());
        }
    }

    public interface OnSlideListener {
        void onSlideClickListener(int position);
    }
}
