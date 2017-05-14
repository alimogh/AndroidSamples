package com.sdwfqin.sample.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideActivity extends AppCompatActivity {

    @BindView(R.id.glid1_img)
    ImageView glid1Img;
    private static final String TAG = "GlideActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.glid1_btn)
    public void onViewClicked() {
        Log.e(TAG, "onViewClicked: " + "加载图片");
        // 图片url（来自必应）
//      String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        String url = "http://p1.pstatp.com/large/166200019850062839d3";
//      String url = "http://12345";
        Glide.with(this) // 创建一个加载图片的实例
                .load(url) // 指定待加载的图片资源(网络图片、本地图片、应用资源、二进制流、Uri对象等等)
                // .asBitmap() // 指定以图片形式显示(默认自动识别)
                // .asGif() // 指定以gif图片显示
                .placeholder(R.drawable.img_tm) // 添加占位图
                .error(R.mipmap.ic_launcher) // 异常占位图
                .crossFade() // 淡入加载效果
                // .animate() // 自定义动画效果
                .priority(Priority.HIGH) // 图片加载优先级，越高越先加载
                // .override(100, 100) // 设置图片大小(以像素为单位)
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 设置缓存策略
                // .into(glid1Img); // 让图片显示在哪个ImageView上
                // 带有监听得into方法
                .into(new GlideDrawableImageViewTarget(glid1Img) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        Log.e(TAG, "onResourceReady: " + "图片加载完成");
                    }
                });
    }
}
