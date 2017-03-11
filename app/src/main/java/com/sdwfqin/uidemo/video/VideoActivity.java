package com.sdwfqin.uidemo.video;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.sdwfqin.uidemo.R;

import java.util.Timer;
import java.util.TimerTask;

public class VideoActivity extends AppCompatActivity {

    private VideoView mVv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVv = (VideoView) findViewById(R.id.vv);
        mVv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.oppo));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 暂停
                mVv.pause();
                // 移动到0秒处（重新播放）
                mVv.seekTo(0);
                // 开始播放
                mVv.start();
            }
        },0,5000);
    }
}
