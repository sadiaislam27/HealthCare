package com.example.health_care;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import com.example.health_care.ListAdapter;

public class RetreiveDataActivity extends AppCompatActivity {

    private ListAdapter adapter ;
    private List<Store> st=new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data);
        adapter = new ListAdapter(RetreiveDataActivity.this);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);

        FloatingActionButton buttonAdd = findViewById(R.id.add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetreiveDataActivity.this,Storing.class));
            }
        });

        downloadData();
    }

    private void downloadData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) return;
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("store").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(!snapshot.exists()){
//                    Toast.makeText(HomeActivity.this, "No data found", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                List<Store> allData = new ArrayList<>();

                for(DataSnapshot ds : snapshot.getChildren()){
                    try{
                        Store data = ds.getValue(Store.class);
                        String key = ds.getKey();
                        if(data == null) continue;
                        data.setKey(key);
                        allData.add(data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                adapter.replaceData(allData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}