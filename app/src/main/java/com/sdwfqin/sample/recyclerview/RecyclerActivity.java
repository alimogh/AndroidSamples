package com.sdwfqin.sample.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.recyclerview.doublelistlinkage.DoublelistlinkageActivity;
import com.sdwfqin.sample.recyclerview.vlayout.VLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：Recycler列表
 *
 * @author zhangqin
 * @date 2017/11/8
 */
public class RecyclerActivity extends AppCompatActivity {

    @BindView(R.id.list)
    ListView mRecyclerList;

    private String[] mTitle = new String[]{"基本使用", "VLayout", "双列表联动"};
    private Class[] mClasses = new Class[]{com.sdwfqin.sample.recyclerview.recycler.RecyclerActivity.class, VLayoutActivity.class,
            DoublelistlinkageActivity.class};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerList.setAdapter(new ArrayAdapter<>(this, R.layout.item_list, R.id.tv_items, mTitle));

        mRecyclerList.setOnItemClickListener((parent, view, position, id) ->
                startActivity(new Intent(RecyclerActivity.this, mClasses[position]))
        );
    }
}
