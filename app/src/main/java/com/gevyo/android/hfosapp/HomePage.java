package com.gevyo.android.hfosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    Button btnView,btnInsert;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth = FirebaseAuth.getInstance();
        btnView = findViewById(R.id.view);
        btnInsert = findViewById(R.id.insert);
    }
    public void onClick(View view){
        if(view == btnView){
            mAuth.signOut();
            Toast.makeText(getApplicationContext(),"Signing out", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            //(new Intent(getApplicationContext(),ViewPage.class));
        }
        if(view == btnInsert){
            startActivity(new Intent(getApplicationContext(),InsertPage.class));
        }
    }
}
