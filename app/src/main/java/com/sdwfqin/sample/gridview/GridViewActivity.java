package com.sdwfqin.sample.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.sdwfqin.sample.R;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    List<SensorBean> mList;
    private GridView mGridView;
    private SensorGridAdapter mAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        mGridView = (GridView) findViewById(R.id.gridview);
        initData();
        mAdpater = new SensorGridAdapter(this, mList);
        mGridView.setAdapter(mAdpater);


    }

    private void initData() {
        mList = new ArrayList<SensorBean>();
        SensorBean mBean;
        String[] mStrings = getResources().getStringArray(R.array.sensor);
        for (int i = 0; i < mStrings.length; i++) {
            mBean = new SensorBean(mStrings[i]);
            mBean.setValue(3 * i);
            mBean.setMaxValue(2 * i + 3);
            mBean.setMinValue(2 * i);
            mList.add(mBean);
        }
    }
}
