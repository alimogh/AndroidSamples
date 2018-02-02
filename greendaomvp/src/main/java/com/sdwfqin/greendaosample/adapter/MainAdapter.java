package com.sdwfqin.greendaosample.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdwfqin.greendaosample.R;
import com.sdwfqin.greendaosample.model.entry.Student;

import java.util.List;

/**
 * Created by zhangqin on 2016/12/19.
 */
// 第一个参数是实体类，第二个参数是ViewHolder(可以自定义，必须继承BaseViewHolder)
public class MainAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {

    public MainAdapter(@LayoutRes int layoutResId, @Nullable List<Student> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Student item) {
        // 添加点击事件
        helper.addOnClickListener(R.id.ll_a)
                .addOnClickListener(R.id.btn_del)
                .addOnClickListener(R.id.btn_xg)
                .setText(R.id.tv_xh, item.getId() + "")
                .setText(R.id.tv_xm, item.getName() + "")
                .setText(R.id.tv_xb, item.getSex() + "")
                .setText(R.id.tv_dz, item.getAddress() + "");
    }
}
