package com.zhangqin.test;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * |================================================================================================
 * |
 * |    作    者：张钦
 * |
 * |    版    本：1.0.1
 * |
 * |    创建时间：2016/2/27  15:03
 * |
 * |    简要描述：SQLite数据库，AlertDialog弹窗，ListView点击事件以及动态刷新
 * |
 * |    遇到问题：空指针异常，数据框创建时少一个“,”，导致无法建立，动态刷新问题
 * |
 * |================================================================================================
 */

public class MainActivity extends AppCompatActivity {

    private MyOpenHelper dbHelper;
    private SQLiteDatabase db;
    private LinearLayout linearLayout;
    List<Student> studentList;
    private LayoutInflater inflater;
    MyAdapter myAdapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取系统资源
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //创建集合
        studentList = new ArrayList<Student>();
        //创建数据库
        dbHelper = new MyOpenHelper(this,"stubook.db",null,1);
        db = dbHelper.getWritableDatabase();
        select();
        lv = (ListView) findViewById(R.id.lv);
        myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);
        //listview长摁事件
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            private String delresult;
            private String delname;

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //设置布局
                linearLayout = (LinearLayout) inflater.inflate(R.layout.del,null);
                //设置标题
                builder.setTitle("删除成绩");
                //设置文本
                builder.setView(linearLayout);

                //获取要删除的内容
                TextView tv_name = (TextView) view.findViewById(R.id.name);
                delname = tv_name.getText().toString();
                TextView tv_result = (TextView) view.findViewById(R.id.result);
                delresult = tv_result.getText().toString();
                //显示在屏幕
                TextView tvname = (TextView) linearLayout.findViewById(R.id.tvname);
                TextView tvresult = (TextView) linearLayout.findViewById(R.id.tvresult);

                tvname.setText(delname);
                tvresult.setText(delresult);

                //设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.delete("book", "name = ?", new String[]{delname});
//                        //移除数据
//                        Student s = new Student(delname,delresult);
//                        studentList.remove(s);
                        //刷新adapter
                        studentList.clear();
                        select();
                        myAdapter.notifyDataSetChanged();
                        //select();
                    }
                });

                //设置取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                //使用创建器,生成一个对话框对象
                AlertDialog ad = builder.create();
                ad.show();
                return false;
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        //系统调用，用来获知集合中有多少条元素
        @Override
        public int getCount() {
            return studentList.size();
        }

        //由系统调用，获取一个View对象，作为ListView的条目
        //position:本次getView方法调用所返回的View对象，在listView中是处于第几个条目，那么position的值就是多少
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Student s = studentList.get(position);
            View v = null;
            //判断条目是否有缓存
            if(convertView == null){
                //把布局文件填充成一个View对象
                v = View.inflate(MainActivity.this, R.layout.layout, null);
            }
            else{
                v = convertView;
            }
            //通过资源id查找组件,注意调用的是View对象的findViewById
            TextView tv_name = (TextView) v.findViewById(R.id.name);
            tv_name.setText(s.getName());
            TextView tv_result = (TextView) v.findViewById(R.id.result);
            tv_result.setText(s.getResult());
            return v;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }

    //添加数据
    public void add (View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        //获取系统资源
//        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //设置布局
        linearLayout = (LinearLayout) inflater.inflate(R.layout.add,null);
        //设置图标
        //builder.setIcon(android.R.drawable.alert_dark_frame);
        //设置标题
        builder.setTitle("添加成绩");
        //设置文本
        //builder.setMessage("设置文本");
        builder.setView(linearLayout);

        //设置确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText etxm = (EditText) linearLayout.findViewById(R.id.etxm);
                EditText etcj = (EditText) linearLayout.findViewById(R.id.etcj);
                String name1 = etxm.getText().toString();
                String result1 = etcj.getText().toString();

                ContentValues values = new ContentValues();
                //组装数据
                values.put("name", name1);
                values.put("result", result1);
                //插入数据
                long i = db.insert("book",null,values);

                if (i == -1){
                    Toast.makeText(MainActivity.this, "写入失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "写入成功", Toast.LENGTH_SHORT).show();
                }
                select(name1);
            }
        });

        //设置取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "取消添加", Toast.LENGTH_SHORT).show();
            }
        });
        //使用创建器,生成一个对话框对象
        AlertDialog ad = builder.create();
        ad.show();
    }
    public void select(){
        Cursor cursor = db.query("book", null, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String result = cursor.getString(2);

            Student s = new Student(name,result);
            studentList.add(s);
        }
    }
    public void select(String name1){
        Cursor cursor = db.query("book", null ,"name = ?", new String[]{name1}, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String result = cursor.getString(2);

            Student s = new Student(name,result);
            studentList.add(s);
        }
    }
}
