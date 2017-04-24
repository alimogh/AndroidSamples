package com.sdwfqin.sample.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.sdwfqin.sample.R;

public class SpinnerActivity extends AppCompatActivity {

    private Spinner mSpinner_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        mSpinner_01 = (Spinner) findViewById(R.id.spinner_01);

        // 建立数据源
        final String[] mItem = getResources().getStringArray(R.array.spinner);
        // 建立Adapter并绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 绑定到Adapter控件
        mSpinner_01.setAdapter(adapter);

        // Spinner响应事件
        mSpinner_01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("tag", "onItemSelected: " + mItem[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 获取下拉框内容
        findViewById(R.id.btn_spinner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = mSpinner_01.getSelectedItem().toString().trim();
                Toast.makeText(SpinnerActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
