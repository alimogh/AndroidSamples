package com.sdwfqin.sample.activitytransition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class T1Activity extends AppCompatActivity {

    @BindView(R.id.anim_list)
    ListView animList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t1);
        ButterKnife.bind(this);

        mContext = this;

        initView();
    }

    private void initView() {
        String[] strings = new String[]{"左进右出"};
        animList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, strings));

        animList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, T2Activity.class));
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        break;
                }
            }
        });
    }
}