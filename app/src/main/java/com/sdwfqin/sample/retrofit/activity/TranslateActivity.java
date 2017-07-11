package com.sdwfqin.sample.retrofit.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.sdwfqin.sample.R;
import com.sdwfqin.sample.retrofit.api.TranslateApi;
import com.sdwfqin.sample.utils.Utils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class TranslateActivity extends AppCompatActivity {

    @BindView(R.id.translate_btn)
    Button translateBtn;
    @BindView(R.id.translate_img)
    ImageView translateImg;
    @BindView(R.id.translate_tv)
    TextView translateTv;

    private static final String TAG = "TranslateActivity";
    private Retrofit mRetrofit;
    private TranslateApi translateApi;
    private static int REQUEST_CODE_CHOOSE = 1;
    private List<Uri> mSelected;
    byte[] datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        ButterKnife.bind(this);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.iotio.org/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava2
                .build();
        translateApi = mRetrofit.create(TranslateApi.class);

        upImg();
    }

    private void upImg() {
        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(TranslateActivity.this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(1)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }
        });
    }

    private void discern(final String md5, byte[] datas) {
        final JsonObject object = new JsonObject();
        object.addProperty("md5", md5);
        object.addProperty("msg", Utils.bytesToHexString(datas));
        Log.e(TAG, "Post: " + object.toString());
        // 调用请求方法，并得到Observable实例
        Observable<String> observable = translateApi.uploadImg(object.toString());
        Log.e(TAG, "Post: " + "获取Observable实例");
        observable.subscribeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public Observable<String> apply(@NonNull String s) throws Exception {
                        Log.e(TAG, "上传图片返回值: " + s);
                        JSONObject jsonObject = new JSONObject(s);

                        JsonObject object1 = new JsonObject();
                        object1.addProperty("md5", md5);
                        object1.addProperty("url", jsonObject.getString("msg"));
                        Log.e(TAG, "识别图片请求json: " + object1.toString());

                        return translateApi.getData(object1.toString());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.e(TAG, "识别图片返回值: " + s);
                        translateTv.setText(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {

            String encodedString;
            String md5;

            mSelected = Matisse.obtainResult(data);

            Glide.with(this) // 创建一个加载图片的实例
                    .load(mSelected.get(0)) // 指定待加载的图片资源(网络图片、本地图片、应用资源、二进制流、Uri对象等等)
                    .placeholder(R.drawable.img_tm) // 添加占位图
                    .error(R.mipmap.ic_launcher)
                    .into(translateImg);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mSelected.get(0));
                encodedString = Utils.bitmapToBase64(bitmap);
                md5 = Utils.stringToMD5(encodedString);
                //图片转字节数组
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                datas = baos.toByteArray();
                discern(md5, datas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
