package com.sdwfqin.uidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sdwfqin.uidemo.achartengine.ChartActivity;
import com.sdwfqin.uidemo.activitytransition.T1Activity;
import com.sdwfqin.uidemo.bottomsheet.BottomSheetActivity;
import com.sdwfqin.uidemo.gridview.GridViewActivity;
import com.sdwfqin.uidemo.handletimer.HandlerTimerActivity;
import com.sdwfqin.uidemo.http.HttpActivity;
import com.sdwfqin.uidemo.notifications.NotificationsActivity;
import com.sdwfqin.uidemo.recycler.RecyclerActivity;
import com.sdwfqin.uidemo.spinner.SpinnerActivity;
import com.sdwfqin.uidemo.sqlite_table.SqliteTableActivity;
import com.sdwfqin.uidemo.time.TimeActivity;
import com.sdwfqin.uidemo.video.VideoActivity;
import com.sdwfqin.uidemo.viewpager.ViewPagerActivity;
import com.sdwfqin.uidemo.wifimanager.WifiManagerActivity;

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
                        startActivity(new Intent(MainActivity.this, SpinnerActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ChartActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, T1Activity.class));
                        break;
                    case 4:
                        MyshowDialog();
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, SqliteTableActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, VideoActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, HttpActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, WifiManagerActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, TimeActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, GridViewActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, BottomSheetActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, HandlerTimerActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
                        break;
                }
            }
        });
    }

    private void MyshowDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("标题")
                .setMessage("内容")
                // 点击空白弹窗不消失
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("确认", null);
        AlertDialog ad = dialog.create();
        Window window = ad.getWindow();
        WindowManager.LayoutParams manager = window.getAttributes();
        manager.alpha = 0.75f;
        window.setAttributes(manager);
        ad.show();
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
