package com.sdwfqin.jsoupdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_tv_test)
    TextView mMainTvTest;

    public final static String TAG = "JsoupDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document mDocument = Jsoup.connect("http://www.juzimi.com/meitumeiju?page=1").get();

                    Elements es = mDocument.getElementsByClass("xlistju");
                    for (Element e : es) {
                        Log.e(TAG, "xlistju===" + e.text().toString());
                    }

                    Elements es1 = mDocument.getElementsByClass("chromeimg");
                    for (Element e : es1) {
                        Log.e(TAG, "chromeimg===" + e.attr("src").toString());
                    }

//                    Log.e(TAG, mDocument.body().toString());
//                    Log.e(TAG, mDocument.head().getAllElements().toString() );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
