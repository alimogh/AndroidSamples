package com.sdwfqin.sample.popupwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sdwfqin.sample.R;

/**
 * 描述：PopDropDownBg
 *
 * @author zhangqin
 * @date 2016/8/18
 */
public class PopDropDownBgActivity extends AppCompatActivity implements View.OnClickListener {

    private PopupWindow mPopWindow;
    private TextView mMenuTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drop_down_bg_activity);
        mMenuTv = findViewById(R.id.menu);
        mMenuTv.setOnClickListener(v -> {
            if (mPopWindow != null) {
                if (mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                } else {
                    showPopupWindow();
                }
            } else {
                showPopupWindow();
            }

        });
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(PopDropDownBgActivity.this).inflate(R.layout.drop_down_bg_activity_popup, null);
        mPopWindow = new PopupWindow(contentView);

        mPopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        backgroundAlpha(0.6f);

        TextView tv1 = contentView.findViewById(R.id.pop_computer);
        TextView tv2 = contentView.findViewById(R.id.pop_financial);
        TextView tv3 = contentView.findViewById(R.id.pop_manage);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        mPopWindow.showAsDropDown(mMenuTv);

        mPopWindow.setOnDismissListener(() -> backgroundAlpha(1f));
    }

    /**
     * 设置背景透明度
     *
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        //0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.pop_computer: {
                Toast.makeText(this, "计算机", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_financial: {
                Toast.makeText(this, "金融", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_manage: {
                Toast.makeText(this, "管理", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            default:
                break;
        }
    }
}
