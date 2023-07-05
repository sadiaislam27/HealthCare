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
    TextView alreadyHaveaccount;
    EditText heartRatee, systolicc, diastolicc;
    Button btn;
    EditText dateEditText;
    EditText timeEditText;
    EditText cmnt;

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference;
    private String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storing);

        Intent intent = getIntent();
        if (intent != null) {
            userEmail = intent.getStringExtra("userEmail");
            if (userEmail != null) {
                // Use the userEmail as needed
            }
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        heartRatee = findViewById(R.id.heartRate);
        systolicc = findViewById(R.id.systolic);
        diastolicc = findViewById(R.id.diastolic);
        dateEditText = findViewById(R.id.date);
        timeEditText = findViewById(R.id.time);
        cmnt = findViewById(R.id.comment);
        btn = findViewById(R.id.save);

        progressDialog = new ProgressDialog(this);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAuthentic();
            }
        });

    }

    private void performAuthentic() {
        String heartRate = heartRatee.getText().toString();
        String systolic = systolicc.getText().toString();
        String diastolic = diastolicc.getText().toString();
        String date = dateEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String cmntt = cmnt.getText().toString();


        String email=userEmail;



        //progressDialog.setMessage("User email: " + userEmail);
        progressDialog.setMessage("Please wait while saving...");
        progressDialog.setTitle("Store");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        // reference = db.getReference("users").child(userEmail).child("store");
        reference = db.getReference("store");
        // Get the current timestamp
        //   long timestamp = System.currentTimeMillis();

        // Create a new Store object with the heart rate, systolic, diastolic, timestamp, and current date
        //   Store store = new Store(heartRate, systolic, diastolic, timestamp, getCurrentDate());


        // Create a new Store object with the heart rate, systolic, and diastolic values


        // Generate a new unique key for the store entry

        String dateTimeString = date + " " + time;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);
        try {
            Date dateTime = format.parse(dateTimeString);
            // Use the dateTime object for further processing or storage
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            // Handle parsing error
        }

        // Create a new Store object with the heart rate, systolic, diastolic, time, and current date
        Store store = new Store(heartRate, systolic, diastolic, time, date,email,cmntt);




        String key = reference.push().getKey();

        // Store the Store object in the "store" path with the generated key
        reference.child(key).setValue(store).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(Storing.this, "Storing is successful", Toast.LENGTH_SHORT).show();
                    sendUserToNextActivity();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Storing.this, "Failed", Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }



    private void sendUserToNextActivity() {
        Intent intent = new Intent(Storing.this, RetreiveDataActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("userEmail", userEmail);
        startActivity(intent);
    }
}
