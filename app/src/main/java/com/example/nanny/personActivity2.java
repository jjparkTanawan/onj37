package com.example.nanny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class personActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_2);

    }

    public void help(View view){
        Intent intent = new Intent(this, help.class);
        startActivity(intent);
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut(); //logout
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
    public void toeditprofile(View view){
        Intent intent = new Intent(this, editprofileActivity2.class);
        startActivity(intent);
    }

    public void tomodepage(View view){
        Intent intent = new Intent(this, modeActivity2.class);
        startActivity(intent);
    }

    public void tononticication(View view){
        Intent intent = new Intent(this, notificationActivity2.class);
        startActivity(intent);
    }

    public void tojoblist (View view){
        Intent intent = new Intent(this, joblistActivity2.class);
        startActivity(intent);
    }



}
