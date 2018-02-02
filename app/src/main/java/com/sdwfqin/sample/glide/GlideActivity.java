package com.sdwfqin.sample.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
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

/**
 * 描述：Glide的用法
 *
 * @author zhangqin
 * @date 2017/11/8
 */
public class GlideActivity extends AppCompatActivity {

    @BindView(R.id.glide_img)
    ImageView mGlideImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.glide_btn)
    public void onViewClicked() {
        LogUtils.i("onViewClicked: " + "加载图片");
        // 图片url（来自必应）
//      String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        String url = "http://p1.pstatp.com/large/166200019850062839d3";
//      String url = "http://12345";
        Glide
                // 创建一个加载图片的实例
                .with(this)
                // 指定待加载的图片资源(网络图片、本地图片、应用资源、二进制流、Uri对象等等)
                .load(url)
                // 指定以图片形式显示(默认自动识别)
                // .asBitmap()
                // 指定以gif图片显示
                // .asGif()
                // 添加占位图
                .placeholder(R.drawable.img_tm)
                // 异常占位图
                .error(R.mipmap.ic_launcher)
                // 淡入加载效果
                .crossFade()
                // 自定义动画效果
                // .animate()
                // 图片加载优先级，越高越先加载
                .priority(Priority.HIGH)
                // 设置图片大小(以像素为单位)
                // .override(100, 100)
                // 设置缓存策略
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                // .into(glid1Img); // 让图片显示在哪个ImageView上
                // 带有监听得into方法
                .into(new GlideDrawableImageViewTarget(mGlideImg) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        LogUtils.i("onResourceReady: " + "图片加载完成");
                    }
                });
    }
}
