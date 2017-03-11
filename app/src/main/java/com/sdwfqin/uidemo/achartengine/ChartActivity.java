package com.sdwfqin.uidemo.achartengine;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.sdwfqin.uidemo.R;
import com.sdwfqin.uidemo.sqlite_table.MySqlite;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdwfqin on 2017/2/23.
 */

public class ChartActivity extends AppCompatActivity {

    private MySqlite mMySqlite;
    private SQLiteDatabase mDb;
    List<Double> list = new ArrayList<Double>();
    private LinearLayout mLayout;
    private LinearLayout mLayout2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mMySqlite = new MySqlite(this);
        mDb = mMySqlite.getWritableDatabase();

        initData();
        initView();
        initBingTu();
    }

    private void initBingTu() {
        mLayout2 = (LinearLayout) findViewById(R.id.chart_bt);

        int[] colors={Color.BLUE,Color.GREEN,Color.MAGENTA};

        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setChartTitle("这是饼图");
        renderer.setChartTitleTextSize(50);
        renderer.setLabelsTextSize(30);// 设置标签字体大小
        renderer.setLabelsColor(Color.BLACK);//设置标签颜色
        renderer.setLegendTextSize(40);//设置左下角表注的文字大小
        renderer.setZoomEnabled(false);//设置不允许放大缩小
        renderer.setPanEnabled(false);//设置是否可以平移
        renderer.setMargins(new int[]{60, 100, 50, 30});// 边距，上左下右
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }

        CategorySeries series = new CategorySeries("饼图");
        series.add("差", 3);
        series.add("不达标", 5);
        series.add("达标", 8);

        mLayout2.addView(ChartFactory.getPieChartView(this,series,renderer));

    }

    private void initView() {
        mLayout = (LinearLayout) findViewById(R.id.chart);

        // 构造渲染器,设置图表整体效果
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

        renderer.setChartTitle("这是图表的标题");
        renderer.setChartTitleTextSize(50);//设置图表标题的字体大小

        renderer.setYTitle("这是纵坐标");

        //颜色设置
        renderer.setApplyBackgroundColor(true);//是否应用设置的背景颜色
        renderer.setBackgroundColor(Color.WHITE);//设置图表的背景颜色
        renderer.setMarginsColor(Color.WHITE);//设置外边距空间的颜色

        renderer.setXLabels(8);//设置x轴显示12个点,根据setChartSettings的最大值和最小值自动计算点的间隔
        renderer.setYLabels(5);//设置y轴显示10个点,根据setChartSettings的最大值和最小值自动计算点的间隔
        renderer.setAxisTitleTextSize(40);//设置轴标题文字的大小
        renderer.setAxesColor(Color.BLACK);//设置坐标轴颜色
        renderer.setLabelsTextSize(30);// 设置标签字体大小
        renderer.setLabelsColor(Color.BLACK);//设置标签颜色
//        renderer.setLegendTextSize(30);//设置图例文字大小
        renderer.setXLabelsAlign(Paint.Align.CENTER);//刻度线与刻度标注之间的相对位置关系
        renderer.setYLabelsAlign(Paint.Align.RIGHT);//刻度线与刻度标注之间的相对位置关系
        renderer.setPointSize(5);// 坐标点大小，圆点
        renderer.setMargins(new int[]{60, 100, 50, 30});// 边距，上左下右
        renderer.setZoomButtonsVisible(true);//是否显示放大缩小按钮
        renderer.setShowGrid(true);//是否显示网格
        renderer.setPanEnabled(true);//图表是否可以移动

        renderer.setShowLegend(true);//控制legend（说明文字 ）是否显示
        renderer.setLegendHeight(200);//设置说明的高度，单位px
        renderer.setLegendTextSize(50);//设置说明字体大小

        // 设置数据源
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries xySeries = new XYSeries("路口");
        int i = 0;
        for (Double s : list) {
            // i横坐标，s纵坐标
            xySeries.add(i++, s);
        }
        dataset.addSeries(xySeries);

        XYSeriesRenderer xySeriesRenderer = new XYSeriesRenderer();
        xySeriesRenderer.setColor(Color.BLACK);
        xySeriesRenderer.setLineWidth(3);//设置线条宽度
        xySeriesRenderer.setDisplayChartValues(true);//是否显示数据值
        xySeriesRenderer.setChartValuesTextAlign(Paint.Align.RIGHT);//数据值的对齐方式（相较于圆点）
        xySeriesRenderer.setChartValuesTextSize(30);//数据值的大小
        xySeriesRenderer.setPointStyle(PointStyle.CIRCLE);//设置接点处的风格
        xySeriesRenderer.setPointStrokeWidth(5);//设置接点处的宽度
        renderer.addSeriesRenderer(xySeriesRenderer);//添加进去

        // 生成视图
        mLayout.addView(ChartFactory.getLineChartView(this, dataset, renderer));

    }

    private void initData() {
        Cursor cursor = mDb.query("usertable", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(2);
                list.add(Double.parseDouble(name));
            } while (cursor.moveToNext());
            cursor.close();

            Log.e("tag", "initData: " + list);
        }
    }
}
