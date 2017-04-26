package com.sdwfqin.sample.view.viewposition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPositionActivity extends AppCompatActivity {

    @BindView(R.id.position_view)
    PositionView positionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_position);
        ButterKnife.bind(this);
        positionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionView.setTranslation();
            }
        });
    }
}
