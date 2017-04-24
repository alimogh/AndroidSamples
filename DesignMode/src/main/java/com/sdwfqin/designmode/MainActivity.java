package com.sdwfqin.designmode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.designmode.singleton.SingletonActivity;

public class MainActivity extends AppCompatActivity {

    private ListView mList;
    private String[] mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (ListView) findViewById(R.id.list);

        mData = getResources().getStringArray(R.array.list);

        mList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, mData));

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("tag", "onItemClick: " + mData[i]);
                switch (i) {
                    case 0:
                        SingletonActivity.getInstance();
                        break;
                }
            }
        });
    }
}