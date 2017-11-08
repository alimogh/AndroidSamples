package com.sdwfqin.sample.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.eventbus.eventbus1.EventBus1Activity;
import com.sdwfqin.sample.eventbus.eventbus2.EventBus2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * EventBus是一个Android端优化的publish/subscribe消息总线，
 * 简化了应用程序内各组件间、组件与后台线程间的通信。
 * <p>
 * 比如请求网络，等网络返回时通过Handler或Broadcast通知UI，
 * 两个Fragment之间需要通过Listener通信，这些需求都可以通过EventBus实现。
 *
 * @author sdwfqin
 */
public class EventBusActivity extends AppCompatActivity {

    @BindView(R.id.eventbus_list)
    ListView mEventbusList;

    private String[] mTitle = new String[]{"入门", "StickyEvent黏性事件"};
    private Class[] mClasses = new Class[]{EventBus1Activity.class, EventBus2Activity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        ButterKnife.bind(this);

        mEventbusList.setAdapter(new ArrayAdapter<>(this, R.layout.item_list, R.id.tv_items, mTitle));

        mEventbusList.setOnItemClickListener((adapterView, view, i, l)
                -> startActivity(new Intent(EventBusActivity.this, mClasses[i]))
        );

    }
}
