package com.sdwfqin.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sdwfqin.sample.activitytransition.T1Activity;
import com.sdwfqin.sample.asynctask.AsyncTaskActivity;
import com.sdwfqin.sample.bottomsheet.BottomSheetActivity;
import com.sdwfqin.sample.canvas.CanvasActivity;
import com.sdwfqin.sample.dagger.DaggerActivity;
import com.sdwfqin.sample.gridview.GridViewActivity;
import com.sdwfqin.sample.handler.HandlerActivity;
import com.sdwfqin.sample.mpandroidchart.ChartActivity;
import com.sdwfqin.sample.notifications.NotificationsActivity;
import com.sdwfqin.sample.popupwindow.PopupActivity;
import com.sdwfqin.sample.recycler.RecyclerActivity;
import com.sdwfqin.sample.retrofit.RetrofitActivity;
import com.sdwfqin.sample.rxjava.RxJavaActivity;
import com.sdwfqin.sample.service.ServiceActivity;
import com.sdwfqin.sample.spannablestring.SpannableActivity;
import com.sdwfqin.sample.broadcast.BroadcastActivity;
import com.sdwfqin.sample.sqlite_table.SqliteTableActivity;
import com.sdwfqin.sample.view.ViewActivity;

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
                        startActivity(new Intent(MainActivity.this, ViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ChartActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, T1Activity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, BottomSheetActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, PopupActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, SqliteTableActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, GridViewActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, HandlerActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, DaggerActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, BroadcastActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, SpannableActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, CanvasActivity.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this, ServiceActivity.class));
                        break;
                    case 17:
                        startActivity(new Intent(MainActivity.this, RxJavaActivity.class));
                        break;
                }
            }
        });
    }

    long exitTime = 0;

    // 双击退出
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
