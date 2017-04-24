package com.sdwfqin.sample.mpandroidchart;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineChartActivity extends Activity {

    @BindView(R.id.chart_line)
    LineChart chartLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_line_chart);
        ButterKnife.bind(this);

        Description description = new Description();
        description.setText("折线图");
        chartLine.setDescription(description);
        chartLine.setNoDataText("没有数据");

    }
}
