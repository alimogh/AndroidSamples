package com.sdwfqin.greendaosample.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
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

import com.sdwfqin.greendaosample.R;
import com.sdwfqin.greendaosample.adapter.MyAdapter;
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

    private LinearLayoutManager mLayoutManager;
    List<Student> mList = new ArrayList<Student>();
    private MyAdapter mMyAdapter;
    private BottomSheetDialog mBottomSheetDialog;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除自带标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToolbarTitle.setVisibility(View.VISIBLE);
        mToolbarTitle.setText("Demo");

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
                initData();
            }
        });

        mMyAdapter = new MyAdapter(this, mList);

        mRecycler.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));
        mRecycler.setAdapter(mMyAdapter);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        initData();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });
    }


    private void initData() {
        Log.e(TAG, "initData: " + "刷新");
        mList.clear();

        mSrl.setRefreshing(false);
        mMyAdapter.notifyDataSetChanged();

    }


    // 搜索栏
    private void initSearchView() {
        MenuItem search = mToolbar.getMenu().findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) search.getActionView();

        searchView.setQueryHint("请输入学号：");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("test", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e("test", s);
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
        final EditText et_age = (EditText) v.findViewById(R.id.et_age);
        final EditText et_address = (EditText) v.findViewById(R.id.et_address);

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xh = et_xh.getText().toString().trim();
                String name = et_name.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String address = et_address.getText().toString().trim();

                if (xh.isEmpty() || name.isEmpty() || age.isEmpty() || address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    mBottomSheetDialog.dismiss();
                    return;
                }

                mMyAdapter.notifyItemInserted(mList.size());

            }
        });
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
        mBottomSheetDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
