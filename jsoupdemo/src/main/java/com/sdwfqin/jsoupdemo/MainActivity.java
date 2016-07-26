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

                String  html="<p>\n" +
                        "\t　　看惯了大漠长河落日，何管那江南缠绵细雨？大漠风沙，落雪梅花，因为久经世味而变得有了些许禅意。然而，不知何时起，我相信前生与今生，一个本不是我能铸就的往事，却一直在我心中徘徊。是因为我是佛前的长明灯，还是我曾经看见凉山上古佛唱经的菩提？<br />\n" +
                        "\t&nbsp;</p>\n" +
                        "<p>\n" +
                        "\t　　大漠漫漫长河，没有江南风荷的淡然；只希望遇一人牧马塞外，看大雪纷飞后红梅缀雪的静染！那时，烧一只陶埙，与那大漠、胡杨共同吹起风沙雪落就已经是最安然的生活&hellip;&hellip;有事感叹：人生像秋风扫落叶般，有时是那么的无情，有时反而给人一种缠绵的感觉！ 看到秋菊凌寒而来，夕阳吐霞而归，那些，那些所谓的伤悲是不是可以放下？塞外牧马，食毡饮雪，虽然所有的一切看似不堪，可对于我来说，或许，那就是最好的去处！ 不入红尘土，何染尘世泥？<br />\n" +
                        "\t&nbsp;</p>\n";
                Document doc = Jsoup.parse(html);
                Elements ele=doc.getElementsByTag("p");
                for(Element e :ele)
                {
                    Log.e(TAG, e.text());

                }


//                try {
//                    Document mDocument = Jsoup.connect("http://www.lookmw.cn/xinxiu/110967.html").get();

//                    Log.e(TAG, "info===" + mDocument.getElementsByClass("info").text().toString());
//                    Log.e(TAG, "hitNum===" + mDocument.getElementsByClass("hitNum").text().toString());
//                    Log.e(TAG, "content===" + mDocument.getElementsByClass("content").text().toString());

//                    Elements es = mDocument.getElementsByClass("content");
//                    for (Element e : es) {
//                        Elements els = e.getElementsByTag("div");
//                        for (Element me : els) {
//                            Log.e(TAG, "div===" + me.text().toString());
//                        }
//
//                    }

//                    Elements es1 = mDocument.getElementsByClass("chromeimg");
//                    for (Element e : es1) {
//                        Log.e(TAG, "chromeimg===" + e.attr("src").toString());
//                    }

//                    Log.e(TAG, mDocument.body().toString());
//                    Log.e(TAG, mDocument.head().getAllElements().toString() );
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }).start();
    }
}
