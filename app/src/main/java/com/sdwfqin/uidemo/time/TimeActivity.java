package com.sdwfqin.uidemo.time;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sdwfqin.uidemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeActivity extends AppCompatActivity {

    private TextView tv_time;
    private SimpleDateFormat mTime;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_time.setText(msg.getData().getString("time"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        tv_time = (TextView) findViewById(R.id.tv_time);

        mTime = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Date nowTime=new Date();

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("time",mTime.format(nowTime));
                message.setData(bundle);

                mHandler.sendMessage(message);
            }
        },0,1000);
    }
}
