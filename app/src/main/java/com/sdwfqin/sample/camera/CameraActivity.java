package com.sdwfqin.sample.camera;

import android.Manifest;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.sdwfqin.sample.R;

import java.util.List;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 描述：自定义相机
 *
 * @author 张钦
 * @date 2018/12/10
 */
public class CameraActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    public static final int PERMISSIONS_CODE_1 = 101;

    private String[] mPerms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    private int cameraType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        // 判断权限
        if (EasyPermissions.hasPermissions(this, mPerms)) {
        } else {
            // 如果用户拒绝权限，第二次打开才会显示提示文字
            EasyPermissions.requestPermissions(this, "使用拍照功能需要拍照权限！", PERMISSIONS_CODE_1, mPerms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "没有权限可能会引起程序崩溃", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    // =============================================================================================

    /**
     * 初始化相机
     */
    public void initCamera() {

    }

    /**
     * 获取照相机实例
     *
     * @return
     */
    private Camera getCamera() {
        Camera camera = null;
        if (CameraUtils.hasCamera()) {
            camera = Camera.open(cameraType);
        } else {
            ToastUtils.showShort("摄像头打开失败");
        }
        return camera;
    }

}
