package com.example.health_care;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.Login;
import com.example.health_care.R;
import com.example.health_care.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class Storing extends AppCompatActivity {
    EditText systolic,diastolic,heart,date,time,comment;
    Button savebutton;
    private Store passedData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storing);

        savebutton=findViewById(R.id.save);
        systolic=findViewById(R.id.systolic);
        diastolic=findViewById(R.id.diastolic);
        heart=findViewById(R.id.heartRate);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        comment=findViewById(R.id.comment);

        Object obj = getIntent().getSerializableExtra("store");


        if(obj instanceof Store){
            passedData = (Store) obj;
            date.setText(passedData.getCurrentDate());
            time.setText(passedData.getTime());
            systolic.setText(passedData.getSystolic());
            diastolic.setText(passedData.getDiastolic());
            heart.setText(passedData.getHeartRate());
            comment.setText(passedData.getComment());

        }

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    private void saveData(){
        String hrt = String.valueOf(heart.getText()).trim();
        String sys = String.valueOf(systolic.getText()).trim();
        String dys = String.valueOf(diastolic.getText()).trim();
        String tm = String.valueOf(time.getText()).trim();
        String dt = String.valueOf(this.date.getText()).trim();
        String cmt = String.valueOf(comment.getText()).trim();

        if(dt.isEmpty() || tm.isEmpty() || sys.isEmpty() || dys.isEmpty() || hrt.isEmpty() || cmt.isEmpty()){
            Toast.makeText(this, "Can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();

        if(uid == null){
            Toast.makeText(Storing.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        Store data = new Store(hrt,sys,dys,tm,dt,cmt);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("store").child(uid);

        String key = null;
        if(passedData == null){
            key = ref.push().getKey();
        }
        else{
            key = passedData.getKey();
        }

        data.setKey(key);

        ref.child(key).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Storing.this, "Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(Storing.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        //FirebaseAuth.getInstance().signOut();;
    }
}
