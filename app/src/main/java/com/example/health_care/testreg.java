package com.example.health_care;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class testreg extends AppCompatActivity {
    TextView alreadyHaveaccount;
    EditText inputEmail,inputPassword,inputCpassword,phone1,name1,age1;
    Button btnRegister;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testreg);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        inputEmail=findViewById(R.id.input_email);
        inputPassword=findViewById(R.id.inputPassword);
        inputCpassword=findViewById(R.id.inputCpassword);
        btnRegister=findViewById(R.id.btnRegister);
        progressDialog=new ProgressDialog(this);
        alreadyHaveaccount = findViewById(R.id.alreadyHave);

        phone1=findViewById(R.id.phone);
        name1=findViewById(R.id.name);
        age1=findViewById(R.id.age);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        alreadyHaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(testreg.this,Login.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAuthentic();
            }
        });

    }

    private void performAuthentic() {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String cpassword=inputCpassword.getText().toString();
        String name=name1.getText().toString();
        String phone=phone1.getText().toString();
        String age=age1.getText().toString();

        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter correct email");
        }
        else if(password.isEmpty() || password.length()<6)
        {
            inputPassword.setError("Enter correct password");
        }
        else if(!password.equals(cpassword))
        {
            inputCpassword.setError("Password doesn't match");
        }
        else
        {
            progressDialog.setMessage("Please wait while registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Users users=new Users(name,phone,age,email,password);
                        reference=db.getReference("users");

                        FirebaseUser user = mAuth.getCurrentUser();
                        if(user == null){
                            Toast.makeText(testreg.this, "User not found", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String uid = user.getUid();

                        reference.child(uid).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    progressDialog.dismiss();
                                    Toast.makeText(testreg.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                                    sendUserToNextActivity();
                                }
                                else{
                                    progressDialog.dismiss();
                                    Toast.makeText(testreg.this, "Failed", Toast.LENGTH_SHORT).show();
                                    task.getException().printStackTrace();
                                }

                            }
                        });

                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(testreg.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }

            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(testreg.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
