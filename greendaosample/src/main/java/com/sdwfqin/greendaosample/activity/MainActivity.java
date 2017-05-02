package com.sdwfqin.greendaosample.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sdwfqin.greendaosample.R;
import com.sdwfqin.greendaosample.adapter.StudentAdapter;
import com.sdwfqin.greendaosample.model.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;

    List<Student> mList = new ArrayList<Student>();
    private BottomSheetDialog mBottomSheetDialog;
    private static final String TAG = "MainActivity";
    private BaseQuickAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除自带标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToolbarTitle.setVisibility(View.VISIBLE);
        mToolbarTitle.setText("GreenDao");

        inflateMenu();
        initSearchView();
        initView();
    }

    private void initView() {

        mSrl.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSrl.setRefreshing(true);
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initAdd();
            }
        });
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        // Adapter
        homeAdapter = new StudentAdapter(R.layout.item_home, mList);
        homeAdapter.openLoadAnimation();

        mRecycler.setAdapter(homeAdapter);

        // 加载数据
        initData();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });
    }

    // 加载数据
    private void initData() {
        Log.e(TAG, "initData: " + "刷新");
        try {
            mList = BaseApplication.getDaoInstant().getStudentDao().loadAll();
        } catch (Exception e) {
            Log.e(TAG, "initData: ", e);
            Toast.makeText(this, "数据读取失败", Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "initData: " + mList.toString());
        mSrl.setRefreshing(false);
        homeAdapter.setNewData(mList);
    }

    // 刷新数据
    private void initAdd() {
        Log.e(TAG, "initData: " + "刷新");
        List<Student> data = new ArrayList<Student>();
        try {
            data = BaseApplication.getDaoInstant().getStudentDao().loadAll();
        } catch (Exception e) {
            Log.e(TAG, "initData: ", e);
            Toast.makeText(this, "数据读取失败", Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "initData: " + mList.toString());
        mSrl.setRefreshing(false);
        homeAdapter.addData(0, data);
//        mRecycler.getLayoutManager().scrollToPosition(0);

        // 获取数据
        mList = homeAdapter.getData();
        Log.e(TAG, "onClick: " + mList.toString());
    }

    // 搜索栏
    private void initSearchView() {
        MenuItem search = mToolbar.getMenu().findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) search.getActionView();

        searchView.setQueryHint("请输入学号：");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e(TAG, s);
                return false;
            }
        });
    }

    private void inflateMenu() {
        mToolbar.inflateMenu(R.menu.menu_frist);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_about:
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/sdwfqin"));
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    public void showBottomSheetDialog() {
        View v = View.inflate(this, R.layout.sheet_dialog, null);
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(v);
        Button btn_enter = (Button) v.findViewById(R.id.btn_enter);
        final EditText et_xh = (EditText) v.findViewById(R.id.et_xh);
        final EditText et_name = (EditText) v.findViewById(R.id.et_name);
        final EditText et_age = (EditText) v.findViewById(R.id.et_sex);
        final EditText et_address = (EditText) v.findViewById(R.id.et_address);

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long xh = 0;
                try {
                    xh = Long.parseLong(et_xh.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "学号请输入数字", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = et_name.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String address = et_address.getText().toString().trim();

                if (xh == 0 || name.isEmpty() || age.isEmpty() || address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    mBottomSheetDialog.dismiss();
                    return;
                }
                Student student = new Student(xh, name, age, address);
                try {
                    BaseApplication.getDaoInstant().getStudentDao().insertOrReplace(student);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onClick: ", e);
                    mBottomSheetDialog.dismiss();
                    return;
                }

//                List<Student> data = new ArrayList<Student>();
//                data.add(student);
//                homeAdapter.addData(0, data);

                homeAdapter.addData(0, student);
                mRecycler.getLayoutManager().scrollToPosition(0);
                // 获取数据
                mList = homeAdapter.getData();
                Log.e(TAG, "onClick: " + mList.toString());
                mBottomSheetDialog.dismiss();

            }
        });
        mBottomSheetDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
