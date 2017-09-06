package com.sdwfqin.sample;

import android.Manifest;
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
import com.sdwfqin.sample.popupwindow.PopupActivity;
import com.sdwfqin.sample.recyclerview.RecyclerActivity;
import com.sdwfqin.sample.retrofit.RetrofitActivity;
import com.sdwfqin.sample.rxjava.RxJavaActivity;
import com.sdwfqin.sample.service.ServiceActivity;
import com.sdwfqin.sample.spannablestring.SpannableActivity;
import com.sdwfqin.sample.sqlite_table.SqliteTableActivity;
import com.sdwfqin.sample.view.ViewActivity;
import com.sdwfqin.sample.webview.WebViewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.list)
    ListView list;

    private final int RESULT_CODE_1 = 100;
    private long exitTime = 0;

    private String[] title = new String[]{"View", "Recycler列表", "Activity跳转动画",
            "BottomSheet", "PopupWindow", "SQLite数据库和动态表格", "GridView", "Handler", "Dagger2",
            "Retrofit", "Broadcast广播", "SpannableString富文本", "Canvas", "AsyncTask", "Service服务",
            "RxJava", "Eventbus", "Glide", "WebView"};
    private Class[] classes = new Class[]{ViewActivity.class, RecyclerActivity.class,
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

        // 判断权限
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            initListener();
        } else {
            // 如果用户拒绝权限，第二次打开才会显示提示文字
            EasyPermissions.requestPermissions(this, "维持App正常运行需要存储权限", RESULT_CODE_1, perms);
        }
    }

    private void initListener() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MainActivity.this, classes[i]));
            }
        });
    }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 同意授权
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        initListener();
    }

    /**
     * 拒绝授权
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "权限", Toast.LENGTH_SHORT).show();
        finish();
    }
}
