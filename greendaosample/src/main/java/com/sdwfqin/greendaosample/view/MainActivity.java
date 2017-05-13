package com.sdwfqin.greendaosample.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
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
import com.sdwfqin.greendaosample.adapter.MainAdapter;
import com.sdwfqin.greendaosample.model.entry.Student;
import com.sdwfqin.greendaosample.model.interactor.MainInteractorImpl;
import com.sdwfqin.greendaosample.presenter.MainPresenter;
import com.sdwfqin.greendaosample.presenter.MainPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener {

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

    private BaseQuickAdapter homeAdapter;
    private BottomSheetDialog mBottomSheetDialog;
    private static final String TAG = "MainActivity";
    private MainPresenter mainPresenter;

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

        mainPresenter = new MainPresenterImpl(this, new MainInteractorImpl());
    }

    private void initView() {

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        // Adapter
        homeAdapter = new MainAdapter(R.layout.item_home, null);
        homeAdapter.openLoadAnimation();

        mRecycler.setAdapter(homeAdapter);

        mSrl.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);

        mFab.setOnClickListener(this);
        mSrl.setOnRefreshListener(this);
        homeAdapter.setOnItemChildClickListener(this);
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

    // 菜单按钮
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

    // 加载数据
    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    // 更新数据
    @Override
    public void upData(final Student student, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("修改");

        View view = View.inflate(MainActivity.this, R.layout.dialog_xg, null);
        final EditText et_name = (EditText) view.findViewById(R.id.et_name);
        final EditText et_sex = (EditText) view.findViewById(R.id.et_sex);
        final EditText et_address = (EditText) view.findViewById(R.id.et_address);

        et_name.setText(student.getName());
        et_sex.setText(student.getSex());
        et_address.setText(student.getAddress());

        builder.setView(view);

        builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = et_name.getText().toString().trim();
                String sex = et_sex.getText().toString().trim();
                String address = et_address.getText().toString().trim();

                if (name.isEmpty() || sex.isEmpty() || address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                student.setName(name);
                student.setSex(sex);
                student.setAddress(address);

                mainPresenter.OnUpData(student, position);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void upAdapter(Student student, int position) {
        // 修改数据
        homeAdapter.remove(position);
        homeAdapter.addData(position, student);
    }

    @Override
    public void upAdapter(int position) {
        // 删除数据
        homeAdapter.remove(position);
    }

    @Override
    public void addAdapter(Student student) {
        homeAdapter.addData(0, student);
        mRecycler.getLayoutManager().scrollToPosition(0);
        mBottomSheetDialog.dismiss();
    }

    // 删除数据
    @Override
    public void delData(Student student, int position) {
        mainPresenter.OnDelData(student, position);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setItems(List<Student> items) {
        homeAdapter.setNewData(items);
    }

    @Override
    public void showProgress() {
        mSrl.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSrl.setRefreshing(false);
    }

    // 添加数据弹窗（增加数据）
    @Override
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

                mainPresenter.OnCreateData(student);

            }
        });
        mBottomSheetDialog.show();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        // 点击浮动按钮
        mainPresenter.createData();
    }

    // 下拉刷新
    @Override
    public void onRefresh() {
        // 重新加载一下数据
        mainPresenter.onResume();
    }

    // 点击条目
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.btn_xg:
                Log.e(TAG, "onItemClick: " + "修改");
                mainPresenter.OnClickUpData((Student) adapter.getItem(position), position);
                break;
            case R.id.btn_del:
                Log.e(TAG, "onItemClick: " + "删除");
                mainPresenter.OnClickDelData((Student) adapter.getItem(position), position);
                break;
            case R.id.ll_a:
                Log.e(TAG, "onItemClick: " + "点击条目");
                break;
        }
    }
}
