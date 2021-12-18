package com.tk17_5.baucucanbo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tk17_5.baucucanbo.Database.DBContext;
import com.tk17_5.baucucanbo.Model.BauChon;

public class MainActivity extends AppCompatActivity {

    EditText edt_masv;
    CheckBox rd_lvt, rd_tth, rd_ttm, rd_lth;
    Button btn_binhchon, btn_kq;
    DBContext dbContext;
    public static int sum = 0;
    public static int lvt = 0;
    public static int tth = 0;
    public static int ttm = 0;
    public static int lth = 0;
    int dem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        dbContext = new DBContext(MainActivity.this);
        btn_binhchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edt_masv.getText().toString().equalsIgnoreCase("")){
                    if(check(Integer.parseInt(edt_masv.getText().toString()))){
                        dem = 0;
                        BauChon bauChon = new BauChon();
                        bauChon.setIdSV(edt_masv.getText().toString());
                        if (rd_lvt.isChecked()) {
                            bauChon.setTvt(true);
                            dem++;
                        }
                        if (rd_ttm.isChecked()) {
                            dem++;
                            bauChon.setTtm(true);
                        }
                        if (rd_lth.isChecked()) {
                            dem++;
                            bauChon.setLth(true);
                        }
                        if (rd_tth.isChecked()) {
                            dem++;
                            bauChon.setTth(true);
                        }
                        if (dem != 3) {
                            Toast.makeText(MainActivity.this, "Phải chọn 3 người", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                            dbContext.bauChon(bauChon);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Mã sinh viên không tồn tại", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(MainActivity.this, "Mã sinh viên chưa được nhập", Toast.LENGTH_SHORT).show();

                }


            }
        });
        btn_kq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sum = 0;
                lvt = 0;
                tth = 0;
                ttm = 0;
                lth = 0;
                loadData();
            }
        });
        findViewById(R.id.btn_danhsach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPause();
                startActivity(new Intent(getApplicationContext(),ListActivity.class));
            }
        });
    }

    private void init() {
        edt_masv = findViewById(R.id.tv_masv);
        rd_lvt = findViewById(R.id.rd_lvt);
        rd_tth = findViewById(R.id.rd_tth);
        rd_ttm = findViewById(R.id.rd_ttm);
        rd_lth = findViewById(R.id.rd_lth);
        btn_binhchon = findViewById(R.id.btn_binhchon);
        btn_kq = findViewById(R.id.btn_ketqua);
    }

    public void loadData() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Đang tải dữ liệu");
        dialog.show();
        dbContext.reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sum = 0;
                lvt = 0;
                tth = 0;
                ttm = 0;
                lth = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    BauChon bauChon = dataSnapshot.getValue(BauChon.class);
                    sum++;
                    if (bauChon.isTvt()) lvt++;
                    if (bauChon.isTtm()) ttm++;
                    if (bauChon.isTth()) tth++;
                    if (bauChon.isLth()) lth++;
                }
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(), KetquaActivity.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public boolean check(int masv){
        boolean kt = false;
        for(int i = 0;i<listsv.length;i++){
            if(masv==listsv[i]) kt = true;
        }
        return kt;
    }
    int[] listsv = new int[]{10119657, 10719245, 10119310, 10119713, 10119084, 10119166, 10118073, 10119239, 10119092, 10119379, 10119639, 10119250, 10119096, 10118021, 10119262, 10119191, 10119263, 10119447, 10119109, 10119112, 10119747, 10119567, 10119122, 10119126, 10119204, 10119050, 10119555, 10119432, 10119364, 10119210, 10119608, 10119540, 10119059, 10119735, 10119065, 10119064, 10119223, 10119225};
}