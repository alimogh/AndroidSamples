package com.sdwfqin.sample.biometrics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.biometrics.fingerprint.FingerprintActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：生物识别
 *
 * @author 张钦
 * @date 2018/10/15
 */
public class BiometricsMainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    ListView mList;

    private String[] mTitle = new String[]{
            "指纹"};
    private Class[] mClasses = new Class[]{
            FingerprintActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        mList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, mTitle));

        initListener();
    }

    private void initListener() {
        mList.setOnItemClickListener((adapterView, view, i, l) ->
                startActivity(new Intent(this, mClasses[i])
                ));
    }
}
