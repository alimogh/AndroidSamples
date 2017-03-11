package com.sdwfqin.uidemo.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sdwfqin.uidemo.R;

public class HttpActivity extends AppCompatActivity {

    private String path = "http://192.168.1.107:8080/transportservice/type/jason/action/GetBusStationInfo.do";
    private String path1 = "http://192.168.1.107:8080/transportservice/type/jason/action/GetAllSense.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        new HttpUtils.Builder()
                .doPost(path1, "{'BusStationId':1}")
                .setOnHttpListener(new HttpUtils.onHttpListener() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("test", response);
                    }

                    @Override
                    public void onError(int code) {

                    }
                })
                .build();
    }
}
