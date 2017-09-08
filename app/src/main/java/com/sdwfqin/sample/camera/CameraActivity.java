package com.sdwfqin.sample.camera;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.sdwfqin.sample.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class CameraActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = "CameraActivity";
    public static final int RESULT_CODE_1 = 201;
    public static final int RESULT_CODE_2 = 202;
    public static final int RESULT_CODE_3 = 203;
    @BindView(R.id.camera_pz)
    Button mCameraPz;
    @BindView(R.id.camera_cc)
    Button mCameraCc;
    @BindView(R.id.camera_img)
    ImageView mCameraImg;
    private String[] mPerms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private Uri mProviderUri;
    private String mPhoto_image;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        // 判断权限
        if (EasyPermissions.hasPermissions(this, mPerms)) {
        } else {
            // 如果用户拒绝权限，第二次打开才会显示提示文字
            EasyPermissions.requestPermissions(this, "使用拍照功能需要拍照权限！", RESULT_CODE_1, mPerms);
        }
    }

    @OnClick({R.id.camera_pz, R.id.camera_cc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.camera_pz:
                pz();
                break;
            case R.id.camera_cc:
                break;
        }
    }

    /**
     * 拍照
     */
    private void pz() {
        mPhoto_image = SDCardUtils.getSDCardPath() + "APPNAME/" + System.currentTimeMillis() + ".jpg";
        File file = new File(mPhoto_image);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Android7.0以上URI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //通过FileProvider创建一个content类型的Uri
            mProviderUri = FileProvider.getUriForFile(this, "com.sdwfqin.sample.fileprovider", file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mProviderUri);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            mUri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        }
        try {
            startActivityForResult(intent, RESULT_CODE_2);
        } catch (ActivityNotFoundException anf) {
            ToastUtils.showShort("摄像头未准备好！");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_CODE_2:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Glide.with(this)
                                .load(mProviderUri)
                                .crossFade()
                                .into(mCameraImg);
                    } else {
                        Glide.with(this)
                                .load(mUri)
                                .crossFade()
                                .into(mCameraImg);
                    }
                    break;
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "没有权限可能会引起程序崩溃", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
