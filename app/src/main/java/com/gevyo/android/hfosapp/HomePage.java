package com.gevyo.android.hfosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {
    Button btnView,btnInsert,btnLogout;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        btnView = findViewById(R.id.view);
        btnInsert = findViewById(R.id.insert);
        btnLogout = findViewById(R.id.logout);

        final DatabaseReference myref = database.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        myref.child("user").child(user.getUid()).child("email").setValue(user.getEmail());
    }
    public void onClick(View view){
        if(view == btnView){
            startActivity(new Intent(getApplicationContext(),ViewPage.class));
        }
        if(view == btnInsert){
            startActivity(new Intent(getApplicationContext(),InsertPage.class));
        }
        if(view == btnLogout){
            mAuth.signOut();
            Toast.makeText(getApplicationContext(),"Signing out", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
}
