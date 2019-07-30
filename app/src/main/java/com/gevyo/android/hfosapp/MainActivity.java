package com.gevyo.android.hfosapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Button btnSignin;
    private EditText mEmail,mPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),HomePage.class));
        }
        mEmail=findViewById(R.id.email);
        mPass=findViewById(R.id.password);
        btnSignin=findViewById(R.id.button_login);




        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d(TAG,"onAuthStateChanged:Signed In as "+user.getUid());
                    Toast.makeText(MainActivity.this,"Succesfully signed in as: "+user.getUid(), Toast.LENGTH_SHORT).show();

                }else{
                    Log.d(TAG,"onAuthStateChanged:Signed Out");
                }
            }
        };
    }
    public void onClick(View view){
        if(view == btnSignin){
            userLogin();
        }
    }

    private void userLogin(){
        String email = mEmail.getText().toString();
        String password = mPass.getText().toString();
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        finish();
                        Toast.makeText(MainActivity.this,"Successfully logged in", Toast.LENGTH_SHORT);
                        startActivity(new Intent(getApplicationContext(),HomePage.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT);
                    }
                }
            });
        }else {
            Log.d(TAG,"Input Reqired for login");
            Toast.makeText(MainActivity.this,"Password atau Email Belum terisi", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
