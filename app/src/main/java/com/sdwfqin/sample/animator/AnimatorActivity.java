package com.sdwfqin.sample.animator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.animator.animator1.Animator1Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimatorActivity extends AppCompatActivity {

    @BindView(R.id.animator_list)
    ListView animatorList;

    private String[] title = new String[]{"button放大"};
    private Class[] classes = new Class[]{Animator1Activity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);

        animatorList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, title));

        animatorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(AnimatorActivity.this, classes[i]));
            }
        });
    }
}
