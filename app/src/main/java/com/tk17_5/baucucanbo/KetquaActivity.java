package com.tk17_5.baucucanbo;

import static com.tk17_5.baucucanbo.MainActivity.lth;
import static com.tk17_5.baucucanbo.MainActivity.lvt;
import static com.tk17_5.baucucanbo.MainActivity.sum;
import static com.tk17_5.baucucanbo.MainActivity.tth;
import static com.tk17_5.baucucanbo.MainActivity.ttm;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tk17_5.baucucanbo.Database.DBContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KetquaActivity extends AppCompatActivity {

    BarChart barChart;
    DBContext dbContext;
    TextView tv_lvt, tv_tth, tv_ttm, tv_lth, tv_tong;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);
        barChart = findViewById(R.id.chart);
        dbContext = new DBContext(KetquaActivity.this);
        setBarChart();
        mediaPlayer = MediaPlayer.create(KetquaActivity.this, R.raw.theme);
        mediaPlayer.start();
        init();
        tv_lvt.setText("Lê Văn Tới: " + lvt + "/" + sum);

        tv_tth.setText("Trần Thị Hường: " + tth + "/" + sum);

        tv_ttm.setText("Thiều Thị Mây: " + ttm + "/" + sum);

        tv_lth.setText("Lê Thị Hường: " + lth + "/" + sum);

        tv_tong.setText("Tổng đại biểu đã bầu: " + sum);


    }

    private void init() {
        tv_lvt = findViewById(R.id.tv_lvt);
        tv_tth = findViewById(R.id.tv_tth);
        tv_ttm = findViewById(R.id.tv_ttm);
        tv_lth = findViewById(R.id.tv_lth);
        tv_tong = findViewById(R.id.tv_tong);
    }

    private void setBarChart() {
        float ratioLVT = ((float)lvt/sum)*100;
        float ratioTTH = ((float)tth/sum)*100;
        float ratioTTM = ((float) ttm/sum)*100;
        float ratioLTH = ((float) lth/sum)*100;
        ArrayList<BarEntry> visitors = new ArrayList<>();

        visitors.add(new BarEntry(2014,(int)ratioLVT ));
        visitors.add(new BarEntry(2015, (int)ratioTTH));
        visitors.add(new BarEntry(2016, (int)ratioTTM));
        visitors.add(new BarEntry(2017, (int)ratioLTH));
        BarDataSet barDataSet = new BarDataSet(visitors,"Bầu cử");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        List<Integer> arr=new ArrayList<>();
        for(int i = 0;i<ColorTemplate.MATERIAL_COLORS.length;i++){
          arr.add(ColorTemplate.MATERIAL_COLORS[i]);
        }
        barDataSet.setValueTextColors(arr);

        barDataSet.setValueTextSize(16f);
        BarData barData = new BarData(barDataSet);
        barChart.getDescription().setText("Bầu cử");
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.animateX(2000);
        barChart.animateY(2000);
        barChart.setHighlightFullBarEnabled(true);
        barChart.setDrawBarShadow(true);
    }
}