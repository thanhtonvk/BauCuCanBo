package com.tk17_5.baucucanbo.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tk17_5.baucucanbo.Model.BauChon;

public class DBContext {
    FirebaseDatabase database;
   public DatabaseReference reference;
    Context context;
    public DBContext(Context context){
        database= FirebaseDatabase.getInstance();
        reference= database.getReference("BauChon");
        this.context= context;
    }
    public void bauChon(BauChon bauChon){
        ProgressDialog dialog= new ProgressDialog(context);
        dialog.setTitle("Đang bình chọn....");
        dialog.show();
        reference.child(bauChon.getIdSV()+"").setValue(bauChon).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(context,"Thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
