package com.sdwfqin.sample.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.retrofit.activity.Retrofit1Activity;
import com.sdwfqin.sample.retrofit.activity.Retrofit2Activity;
import com.sdwfqin.sample.retrofit.activity.Retrofit3Activity;
import com.sdwfqin.sample.retrofit.activity.TranslateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：Retrofit网络请求
 *
 * @author zhangqin
 * @date 2017/11/8
 */
public class RetrofitActivity extends AppCompatActivity {

    @BindView(R.id.retrofit_list)
    ListView mRetrofitList;

    private String[] mTitle = new String[]{"入门例子", "get请求", "post请求+RxJava", "Translate图片识别"};
    private Class[] mClasses = new Class[]{Retrofit1Activity.class, Retrofit2Activity.class,
            Retrofit3Activity.class, TranslateActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        mRetrofitList.setAdapter(new ArrayAdapter<>(this, R.layout.item_list, R.id.tv_items, mTitle));

        mRetrofitList.setOnItemClickListener((parent, view, position, id) ->
                startActivity(new Intent(RetrofitActivity.this, mClasses[position]))
        );
    }
}
