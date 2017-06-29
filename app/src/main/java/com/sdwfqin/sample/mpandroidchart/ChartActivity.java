package com.sdwfqin.sample.mpandroidchart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.mpandroidchart.chartactivity.BarChartActivity;
import com.sdwfqin.sample.mpandroidchart.chartactivity.CombineChartActivity;
import com.sdwfqin.sample.mpandroidchart.chartactivity.LineChartActivity;
import com.sdwfqin.sample.mpandroidchart.chartactivity.MultiLineChartActivity;
import com.sdwfqin.sample.mpandroidchart.chartactivity.PieChartActivity;
import com.sdwfqin.sample.mpandroidchart.chartactivity.PositiveNegativeBarChartActivity;
import com.sdwfqin.sample.mpandroidchart.chartactivity.ThreeBarChartActivity;
import com.sdwfqin.sample.mpandroidchart.chartactivity.TwoBarChartActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdwfqin on 2017/2/23.
 */
public class ChartActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, initListData()));
        listView.setOnItemClickListener(this);
    }

    private List<String> initListData() {
        List<String> data = new ArrayList<>();
        data.add("柱状图（单）");
        data.add("柱状图（双）");
        data.add("柱状图（三）");
        data.add("正负柱状图");
        data.add("折线图（单）");
        data.add("折线图（复）");
        data.add("饼图");
        data.add("组合图");
        return data;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i;
        switch (position) {
            case 0:
                i = new Intent(ChartActivity.this, BarChartActivity.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(ChartActivity.this, TwoBarChartActivity.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(ChartActivity.this, ThreeBarChartActivity.class);
                startActivity(i);
                break;
            case 3:
                i = new Intent(ChartActivity.this, PositiveNegativeBarChartActivity.class);
                startActivity(i);
                break;
            case 4:
                i = new Intent(ChartActivity.this, LineChartActivity.class);
                startActivity(i);
                break;
            case 5:
                i = new Intent(ChartActivity.this, MultiLineChartActivity.class);
                startActivity(i);
                break;
            case 6:
                i = new Intent(ChartActivity.this, PieChartActivity.class);
                startActivity(i);
                break;
            case 7:
                i = new Intent(ChartActivity.this, CombineChartActivity.class);
                startActivity(i);
                break;
        }
    }
}
