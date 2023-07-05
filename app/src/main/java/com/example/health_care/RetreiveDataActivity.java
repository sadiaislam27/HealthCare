package com.example.health_care;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetreiveDataActivity extends AppCompatActivity {

    ListView myListview;
    List<Store> usersList;
    Button add;
    DatabaseReference usersDbRef;
    String userEmail;
    Intent intent = getIntent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data);
        Intent intent = getIntent();
        if (intent != null) {
            userEmail = intent.getStringExtra("userEmail");
            if (userEmail != null) {
                // Use the userEmail as needed
            }
        }


        myListview = findViewById(R.id.myListView);

        usersList = new ArrayList<>();

        usersDbRef = FirebaseDatabase.getInstance().getReference("store");

        usersDbRef.orderByChild("email").equalTo(userEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();

                for (DataSnapshot userDatasnap : dataSnapshot.getChildren()) {
                    Store user = userDatasnap.getValue(Store.class);
                    usersList.add(user);
                }

                ListAdapter adapter = new ListAdapter(RetreiveDataActivity.this, usersList);
                myListview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RetreiveDataActivity.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
            }
        });

        add = findViewById(R.id.add);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RetreiveDataActivity.this, Storing.class);
                intent.putExtra("userEmail", userEmail); // Pass the userEmail as an extra
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

}
