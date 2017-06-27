package com.sdwfqin.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sdwfqin.sample.activitytransition.T1Activity;
import com.sdwfqin.sample.asynctask.AsyncTaskActivity;
import com.sdwfqin.sample.bottomsheet.BottomSheetActivity;
import com.sdwfqin.sample.broadcast.BroadcastActivity;
import com.sdwfqin.sample.canvas.CanvasActivity;
import com.sdwfqin.sample.dagger.DaggerActivity;
import com.sdwfqin.sample.eventbus.EventBusActivity;
import com.sdwfqin.sample.glide.GlideActivity;
import com.sdwfqin.sample.gridview.GridViewActivity;
import com.sdwfqin.sample.handler.HandlerActivity;
import com.sdwfqin.sample.mpandroidchart.ChartActivity;
import com.sdwfqin.sample.popupwindow.PopupActivity;
import com.sdwfqin.sample.recycler.RecyclerActivity;
import com.sdwfqin.sample.retrofit.RetrofitActivity;
import com.sdwfqin.sample.rxjava.RxJavaActivity;
import com.sdwfqin.sample.service.ServiceActivity;
import com.sdwfqin.sample.spannablestring.SpannableActivity;
import com.sdwfqin.sample.sqlite_table.SqliteTableActivity;
import com.sdwfqin.sample.view.ViewActivity;
import com.sdwfqin.sample.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    ListView list;

    private String[] title = new String[]{"View", "Recycler列表", "MpAndroidChart", "Activity跳转动画",
            "BottomSheet", "PopupWindow", "SQLite数据库和动态表格", "GridView", "Handler", "Dagger2",
            "Retrofit", "Broadcast广播", "SpannableString富文本", "Canvas", "AsyncTask", "Service服务",
            "RxJava", "Eventbus", "Glide", "WebView"};
    private Class[] classes = new Class[]{ViewActivity.class, RecyclerActivity.class, ChartActivity.class,
            T1Activity.class, BottomSheetActivity.class, PopupActivity.class, SqliteTableActivity.class,
            GridViewActivity.class, HandlerActivity.class, DaggerActivity.class, RetrofitActivity.class,
            BroadcastActivity.class, SpannableActivity.class, CanvasActivity.class, AsyncTaskActivity.class,
            ServiceActivity.class, RxJavaActivity.class, EventBusActivity.class, GlideActivity.class,
            WebViewActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, title));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MainActivity.this, classes[i]));
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
