package com.sdwfqin.sample.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.retrofit.activity.Retrofit1Activity;
import com.sdwfqin.sample.retrofit.activity.Retrofit2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetrofitActivity extends AppCompatActivity {

    @BindView(R.id.retrofit_list)
    ListView retrofitList;

    private String[] title = new String[]{"入门例子", "get请求"};
    private Class[] classes = new Class[]{Retrofit1Activity.class, Retrofit2Activity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        retrofitList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, title));

        retrofitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(RetrofitActivity.this, classes[position]));
            }
        });
    }
}
