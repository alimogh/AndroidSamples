package com.sdwfqin.sample.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sdwfqin.sample.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zhangqin
 */
public class HandlerActivity extends AppCompatActivity {

    // 状态锁
    private static Boolean isGuang = true;
    private static final String TAG = "HandlerActivity";
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        Message message = new Message();
        message.what = 1;
        handlerA.sendMessage(message);

        // 在子线程发送Message
        new Thread(() -> handlerA.sendEmptyMessage(2)).start();

        // 延时5s发送数据
        new Handler().postDelayed(() -> handlerA.sendEmptyMessage(3), 5000);
    }

    private final Handler handlerA = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e(TAG, "handlerA:case:1");
                    Log.e(TAG, "这是来自主线程的Handler");
                    // 计时器
                    timer = new Timer();
                    /**
                     *
                     * 延时循环（延时1000ms调用一次task，每2000ms一次）
                     *
                     * task :   TimerTask
                     * 1000 :   延时多久执行
                     * 2000 :   每隔多长时间执行一次
                     *
                     */
                    timer.schedule(task, 1000, 2000);
                    break;
                case 2:
                    Log.e(TAG, "handlerA:case:2");
                    Log.e(TAG, "这是来自主线程的子线程的Handler");
                    new Thread(new Runnable() {

                        @Override
                        public synchronized void run() {
                            if (isGuang) {
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                handlerB.sendEmptyMessage(0x02);
                                isGuang = false;
                                Log.e(TAG, "isGuang = " + isGuang);
                            }

                        }
                    }).start();
                    break;
                case 3:
                    Log.e(TAG, "handlerA:case:3");
                    break;
                default:
                    break;
            }
        }
    };

    private final Handler handlerB = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x01:
                    Log.e(TAG, "handlerB:case:0x01");
                    Log.e(TAG, "这是来自主线程的handlerA的计时器的Handler");
                    break;
                case 0x02:
                    Log.e(TAG, "handlerB:case:0x02");
                    Log.e(TAG, "这是来自主线程的handlerA的子线程的Handler");
                    new Thread(new Runnable() {

                        @Override
                        public synchronized void run() {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            isGuang = true;
                            Log.e(TAG, "isGuang = " + isGuang);

                            // 计时器
                            Timer timer = new Timer();
                            // 延时1000ms
                            timer.schedule(new TimerTask() {

                                @Override
                                public void run() {
                                    Log.e(TAG, "这是来自计时器的第一条消息");
                                    try {
                                        // 休眠1000ms
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Log.e(TAG, "这是来自计时器的第二条消息");
                                }
                            }, 1000);
                        }
                    }).start();
                    break;

                default:
                    break;
            }
        }

        ;
    };

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            handlerB.sendEmptyMessage(0x01);
        }
    };

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
