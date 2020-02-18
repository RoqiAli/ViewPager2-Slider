package com.devcreature.viewpager2slider;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.devcreature.viewpager2slider.adapter.MainAdapter;
import com.devcreature.viewpager2slider.data.EvolveData;
import com.devcreature.viewpager2slider.model.EvolveModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MainAdapter.OnSlideListener {

    private ViewPager2 viewPager2;
    private ArrayList<EvolveModel> models = new ArrayList<>();
    private Handler handler = new Handler();
    private MainAdapter mainAdapter;
    private LinearLayout linearLayout;

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearlayout);
        viewPager2 = findViewById(R.id.view_pager);

        models.addAll(EvolveData.getListData());
        mainAdapter = new MainAdapter(models, viewPager2, this);
        viewPager2.setAdapter(mainAdapter);

        setupIndicator();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int pos = position % linearLayout.getChildCount();
                updateIndicator(pos);
                handler.removeCallbacks(sliderRunnable);
                handler.postDelayed(sliderRunnable, 5000);
            }
        });
    }

    private void setupIndicator() {
        ImageView[] dots = new ImageView[mainAdapter.getItemCount()];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 8, 0);
        params.gravity = Gravity.CENTER;
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getApplicationContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_unselect));
            dots[i].setLayoutParams(params);
            linearLayout.addView(dots[i]);
        }
    }

    private void updateIndicator(int idx) {
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) linearLayout.getChildAt(i);
            if (i == idx) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_select));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_unselect));
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(sliderRunnable, 5000);
    }

    @Override
    public void onSlideClickListener(int position) {
        Toast.makeText(this, ""+models.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
}
