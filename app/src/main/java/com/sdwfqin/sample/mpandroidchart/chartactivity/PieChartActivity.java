package com.sdwfqin.sample.mpandroidchart.chartactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.sdwfqin.sample.R;
import com.sdwfqin.sample.mpandroidchart.util.MPChartHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class PieChartActivity extends AppCompatActivity {

    private PieChart pieChart;
    private Map<String,Float> pieValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        initView();
        initData();
        MPChartHelper.setPieChart(pieChart,pieValues,"饼图",true);
    }

    private void initView(){
        pieChart=(PieChart)findViewById(R.id.pieChart);
    }

    private void initData(){
        pieValues=new LinkedHashMap<>();
        pieValues.put("A",100f);
        pieValues.put("B",150f);
        pieValues.put("C",30f);
        pieValues.put("D",70f);
    }
}
