package com.sdwfqin.sample.view.viewz2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewZ2Activity extends AppCompatActivity {

    @BindView(R.id.viewz2_seek)
    SeekBar viewz2Seek;
    @BindView(R.id.viewz2_main)
    ViewZ2View viewz2Main;
    @BindView(R.id.viewz2_seek2)
    SeekBar viewz2Seek2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_z2);
        ButterKnife.bind(this);

        viewz2Seek.setProgress(viewz2Main.getRadius());
        viewz2Seek2.setProgress(viewz2Main.getGap());

        // 圆的半径
        viewz2Seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewz2Main.setRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // 圆的间距
        viewz2Seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewz2Main.setGap(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
