package com.sdwfqin.sample.popupwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sdwfqin.sample.R;

/**
 * Created by sdwfqin on 2016/8/18.
 */
public class PopDropDownBgActivity extends AppCompatActivity implements View.OnClickListener {
    private PopupWindow mPopWindow;
    private TextView mMenuTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drop_down_bg_activity);
        mMenuTv = (TextView) findViewById(R.id.menu);
        mMenuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopWindow != null) {
                    if (mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    } else {
                        showPopupWindow();
                    }
                } else {
                    showPopupWindow();
                }

            }
        });
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(PopDropDownBgActivity.this).inflate(R.layout.drop_down_bg_activity_popup, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        TextView tv1 = (TextView) contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView) contentView.findViewById(R.id.pop_financial);
        TextView tv3 = (TextView) contentView.findViewById(R.id.pop_manage);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        mPopWindow.showAsDropDown(mMenuTv);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.pop_computer: {
                Toast.makeText(this, "clicked computer", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_financial: {
                Toast.makeText(this, "clicked financial", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_manage: {
                Toast.makeText(this, "clicked manage", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
        }
    }
}
