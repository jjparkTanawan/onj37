package com.example.nanny;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class cometoRegister extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.come);
    }

    public void regisNanny(View view){
        Intent intent = new Intent(this, RegisterActivity2.class);
        startActivity(intent);
    }

    public void regisBaby (View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void backtologin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
