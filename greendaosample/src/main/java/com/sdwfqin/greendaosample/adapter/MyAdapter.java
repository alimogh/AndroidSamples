package com.sdwfqin.greendaosample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sdwfqin.greendaosample.R;
import com.sdwfqin.greendaosample.model.Student;

import java.util.List;

/**
 * Created by sdwfqin on 2016/12/19.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyItemData> {

    private Context mContext;
    private List<Student> mDatas;

    public MyAdapter(Context context, List<Student> datas) {
        mContext = context;
        mDatas = datas;
    }

    //创建布局
    @Override
    public MyItemData onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_home, parent, false);

        return new MyItemData(view);
    }

    //设置数据
    @Override
    public void onBindViewHolder(final MyItemData holder, int position) {

        final Student data = mDatas.get(position);
        holder.bindData(data);

        // 删除
        holder.mBtn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.mBtn_xg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.mBtn_zd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.ll_a.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext, "长按" + data.getId() + "", Toast.LENGTH_SHORT).show();
                // 返回true不触发OnClickListener
                return true;
            }
        });
        holder.ll_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, data.getId() + "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // 定义ViewHolder内部类
    class MyItemData extends RecyclerView.ViewHolder {

        LinearLayout ll_a;
        TextView tv_xh;
        TextView tv_xm;
        TextView tv_xb;
        TextView tv_dz;
        Button mBtn_zd;
        Button mBtn_xg;
        Button mBtn_del;

        public MyItemData(View itemView) {
            super(itemView);

            ll_a = (LinearLayout) itemView.findViewById(R.id.ll_a);
            tv_xh = (TextView) itemView.findViewById(R.id.tv_xh);
            tv_xm = (TextView) itemView.findViewById(R.id.tv_xm);
            tv_xb = (TextView) itemView.findViewById(R.id.tv_xb);
            tv_dz = (TextView) itemView.findViewById(R.id.tv_dz);
            // 按钮
            mBtn_zd = (Button) itemView.findViewById(R.id.btn_zd);
            mBtn_xg = (Button) itemView.findViewById(R.id.btn_xg);
            mBtn_del = (Button) itemView.findViewById(R.id.btn_del);
        }

        public void bindData(Student student) {

            Log.e("test", "bindData");
            tv_xh.setText(student.getId() + "");
            tv_xm.setText(student.getName());
            tv_xb.setText(student.getAge());
            tv_dz.setText(student.getAddress());
            if (student.getTop() == 1) {
                mBtn_zd.setText("取消置顶");
                ll_a.setBackgroundResource(R.color.topbg);
            } else {
                mBtn_zd.setText("置顶");
                ll_a.setBackgroundColor(0xffffff);
                //?android:attr/selectableItemBackground
            }
        }


    }
}
