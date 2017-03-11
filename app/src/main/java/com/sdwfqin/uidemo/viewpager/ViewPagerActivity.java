package com.sdwfqin.uidemo.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdwfqin.uidemo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private List<View> mViews;
    private PagerAdapter mPagerAdapter;
    private ViewPager mVp_ydpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        //第1步，创建一个View的集合，并动态加载n个View，添加进集合
        LayoutInflater inflater = LayoutInflater.from(this);
        mViews = new ArrayList<View>();
        mViews.add(inflater.inflate(R.layout.page_one,null));
        mViews.add(inflater.inflate(R.layout.page_two,null));
        mViews.add(inflater.inflate(R.layout.page_three,null));
        //第2步，为ViewAdapter装载这个集合
        mPagerAdapter = new PagerAdapter() {

            // 加载View的方法，类似于AdapterView中的getView方法
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                //添加页卡(遍历View这个集合，加载所有View作为页卡）
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            // 直接返回View集合的大小即可
            @Override
            public int getCount() {
                return mViews.size();
            }

            //判断当前的View是否是我们需要的对象
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            //不要的时候，销毁View的方法,根据position进行索引，并制定View为ViewPager
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }
        };

        //第3步，给ViewPager设置ViewAdapter
        mVp_ydpage = (ViewPager) findViewById(R.id.vp_ydpage);
        mVp_ydpage.setAdapter(mPagerAdapter);
    }
}