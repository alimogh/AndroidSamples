package com.sdwfqin.sample.view.rippleanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RippleAnimationActivity extends AppCompatActivity {

    @BindView(R.id.ImageView)
    android.widget.ImageView ImageView;
    @BindView(R.id.layout_RippleAnimation)
    RippleAnimationView layoutRippleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_animation);
        ButterKnife.bind(this);

        ImageView = (ImageView) findViewById(R.id.ImageView);
        layoutRippleAnimation = (RippleAnimationView) findViewById(R.id.layout_RippleAnimation);

        ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutRippleAnimation.isRippleRunning()) {
                    layoutRippleAnimation.stopRippleAnimation();
                } else {
                    layoutRippleAnimation.startRippleAnimation();
                }
            }
        });
    }
}
