package com.sdwfqin.sample.webview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.webview_btn)
    Button webviewBtn;

    private Context mContext;
    private static final String TAG = "WebViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        mContext = this;

        initWeb();

        // 调用js中的setName方法
        webviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:setName('测试与js交互')");
            }
        });

    }

    private void initWeb() {
        WebSettings webSettings = webview.getSettings();

        // 启用js
        webSettings.setJavaScriptEnabled(true);
        // 禁止缩放
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);

        // WebView 背景透明效果
        // webview.setBackgroundColor(Color.TRANSPARENT);
        webview.addJavascriptInterface(new JsInteration(), "app");
        webview.loadUrl("file:///android_asset/html/webtest.html");
    }

    public class JsInteration {
        @JavascriptInterface
        public void showName(String name) {
            Log.e(TAG, "showName: " + name);
            Toast.makeText(mContext, "name=" + name, Toast.LENGTH_LONG).show();
        }
    }
}
