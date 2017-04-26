package com.sdwfqin.sample.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.view.gesturedetector.GestureDetectorActivity;
import com.sdwfqin.sample.view.motionslop.MeTsActivity;
import com.sdwfqin.sample.view.scroller.ScrollerActivity;
import com.sdwfqin.sample.view.viewposition.ViewPositionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewActivity extends AppCompatActivity {

    @BindView(R.id.view_list)
    ListView viewList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        String[] strings = new String[]{"View的位置参数", "MotionEvent与TouchSlop", "GestureDetector", "Scroller"};
        viewList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, strings));

        viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, ViewPositionActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, MeTsActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, GestureDetectorActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mContext, ScrollerActivity.class));
                        break;
                }
            }
        });
    }
}
