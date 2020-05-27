package com.sdwfqin.sample.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：相机开发
 *
 * @author 张钦
 * @date 2018/12/10
 */
public class CameraMainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    ListView list;

    private String[] mTitle = new String[]{
            "调用系统相机",
            "自定义相机",
    };
    private Class[] mClasses = new Class[]{
            PictureActivity.class,
            CameraActivity.class,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        list.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, mTitle));
        initListener();
    }

    private void initListener() {
        list.setOnItemClickListener((adapterView, view, i, l) ->
                startActivity(new Intent(CameraMainActivity.this, mClasses[i])
                ));
    }
}
