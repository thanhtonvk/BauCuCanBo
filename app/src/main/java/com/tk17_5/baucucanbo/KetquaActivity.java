package com.tk17_5.baucucanbo;

import static com.tk17_5.baucucanbo.MainActivity.lth;
import static com.tk17_5.baucucanbo.MainActivity.lvt;
import static com.tk17_5.baucucanbo.MainActivity.sum;
import static com.tk17_5.baucucanbo.MainActivity.tth;
import static com.tk17_5.baucucanbo.MainActivity.ttm;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tk17_5.baucucanbo.Database.DBContext;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KetquaActivity extends AppCompatActivity {

    PieChart barChart;
    DBContext dbContext;
    TextView tv_lvt, tv_tth, tv_ttm, tv_lth, tv_tong;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);
        barChart = findViewById(R.id.barchart);
        dbContext = new DBContext(KetquaActivity.this);

        mediaPlayer = MediaPlayer.create(KetquaActivity.this, R.raw.theme);
        mediaPlayer.start();
        init();
        tv_lvt.setText("Lê Văn Tới: " + lvt + "/" + sum);
        tv_lvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBarChart("Lê Văn Tới", lvt);
            }
        });
        tv_tth.setText("Trần Thị Hường: " + tth + "/" + sum);
        tv_tth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBarChart("Trần Thị Hường", tth);
            }
        });
        tv_ttm.setText("Thiều Thị Mây: " + ttm + "/" + sum);
        tv_ttm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBarChart("Thiều Thị Mây", ttm);
            }
        });
        tv_lth.setText("Lê Thị Hường: " + lth + "/" + sum);
        tv_lth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBarChart("Lê Thị Hường", lth);
            }
        });
        tv_tong.setText("Tổng đại biểu đã bầu: " + sum);


    }

    private void init() {
        tv_lvt = findViewById(R.id.tv_lvt);
        tv_tth = findViewById(R.id.tv_tth);
        tv_ttm = findViewById(R.id.tv_ttm);
        tv_lth = findViewById(R.id.tv_lth);
        tv_tong = findViewById(R.id.tv_tong);
    }

    private void setBarChart(String name, int count) {
        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry((float) (count * 1.0 / sum * 100), name));
        visitors.add(new PieEntry((float) (100 - (count * 1.0 / sum * 100)), "Khác"));
        PieDataSet pieDataSet = new PieDataSet(visitors, "");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);
        PieData pieData = new PieData(pieDataSet);
        barChart.setData(pieData);
        barChart.getDescription().setEnabled(false);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        String str = "Tỉ lệ bầu " + name + ": " + df.format(count * 1.0 / sum * 100) + "%";
        barChart.setCenterText(str);
        barChart.animate();
    }
}