package com.sdwfqin.sample.popupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sdwfqin.sample.R;

/**
 * Created by sdwfqin on 2016/8/18.
 */
public class PopshowAtLocationActivity extends AppCompatActivity implements View.OnClickListener {
    private PopupWindow mPopWindow;
    private Button mBtn;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_at_location_activity);

        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(PopshowAtLocationActivity.this).inflate(R.layout.show_at_location_activity_popup, null);

        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);

        TextView tv1 = (TextView) contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView) contentView.findViewById(R.id.pop_financial);
        TextView tv3 = (TextView) contentView.findViewById(R.id.pop_manage);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        View rootview = LayoutInflater.from(PopshowAtLocationActivity.this).inflate(R.layout.show_at_location_activity, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
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
        }
    }
}
