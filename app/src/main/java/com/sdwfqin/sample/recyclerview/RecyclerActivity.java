package com.sdwfqin.sample.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.recyclerview.doublelistlinkage.DoublelistlinkageActivity;
import com.sdwfqin.sample.recyclerview.recycler1.Recycler1Activity;
import com.sdwfqin.sample.recyclerview.vlayout.VLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerActivity extends AppCompatActivity {

    @BindView(R.id.recycler_list)
    ListView recyclerList;

    private String[] title = new String[]{"基本使用", "VLayout", "双列表联动"};
    private Class[] classes = new Class[]{Recycler1Activity.class, VLayoutActivity.class,
            DoublelistlinkageActivity.class};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        recyclerList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, title));

        recyclerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(RecyclerActivity.this, classes[position]));
            }
        });
    }
}
