package com.example.nanny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class modeActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_2);
    }

    public void tononticication(View view){
        Intent intent = new Intent(this, notificationActivity2.class);
        startActivity(intent);
    }

    public void tojoblist (View view){
        Intent intent = new Intent(this, joblistActivity2.class);
        startActivity(intent);
    }

    public void topersonpage(View view){
        Intent intent = new Intent(this, personActivity2.class);
        startActivity(intent);
    }

}
