package com.tk17_5.baucucanbo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tk17_5.baucucanbo.Database.DBContext;
import com.tk17_5.baucucanbo.Model.BauChon;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dbContext = new DBContext(ListActivity.this);
        lv = findViewById(R.id.lv);
        loadData();
    }

    DBContext dbContext;
    ListView lv;
    List<BauChon> bauChonList;
    ArrayAdapter adapter;

    public void loadData() {
        dbContext.reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bauChonList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    BauChon bauChon = dataSnapshot.getValue(BauChon.class);
                    bauChonList.add(bauChon);
                }
                adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, bauChonList);
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}