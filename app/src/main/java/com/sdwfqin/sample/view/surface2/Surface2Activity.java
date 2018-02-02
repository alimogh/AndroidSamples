package com.sdwfqin.sample.view.surface2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.sdwfqin.sample.Config;
import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：SurfaceView画板
 *
 * @author zhangqin
 * @date 2017/6/27
 */
public class Surface2Activity extends AppCompatActivity {

    @BindView(R.id.surface)
    DbSurfaceView mSurface;
    @BindView(R.id.btn_save)
    TextView mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface2);
        ButterKnife.bind(this);

        mBtnSave.setOnClickListener(view -> {
            // TODO: 这里有问题
            // view转为bitmap
            Bitmap bitmap = ImageUtils.view2Bitmap(mSurface);
            LogUtils.i(Config.SAVE_REAL_PATH);
            boolean save = ImageUtils.save(bitmap, Config.SAVE_REAL_PATH + "/" + System.currentTimeMillis() + ".jpg", Bitmap.CompressFormat.JPEG);
            if (save) {
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
