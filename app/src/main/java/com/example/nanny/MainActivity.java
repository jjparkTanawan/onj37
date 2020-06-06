package com.example.nanny;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    EditText mail2,pass2;
    TextView register;
    Button login2;
    FirebaseAuth auth;

    private DatabaseReference databaseReference1,databaseReference2;
    private FirebaseDatabase firebaseDatabase;
    private static final String BABY = "Baby Data";
    private static final String MEMBER = "Nanny Data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_1);

        mail2 = findViewById(R.id.email2);
        pass2 = findViewById(R.id.pass2);
        register = findViewById(R.id.register);
        login2 = findViewById(R.id.choose_image_btn);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase.getReference(BABY);
        databaseReference2 = firebaseDatabase.getReference(MEMBER);

        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail2.getText().toString();
                final String password = pass2.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your mail address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        pass2.setError("ใส่รหัสผ่านใหม่");
                                    } else {
                                        mail2.setError("ใส่อีเมล์ใหม่");
//                                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    databaseReference1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                                if ((mail2.getText().toString()).equals(childSnapshot.child("mail_b").getValue(String.class))) {
                                                    Intent intent = new Intent(MainActivity.this, homeActivity1.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                    databaseReference2.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                                if ((mail2.getText().toString()).equals(childSnapshot.child("mail_n").getValue(String.class))) {
                                                    Intent intent = new Intent(MainActivity.this, modeActivity2.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        });
            }
        });

    }

    public void toregister(View view) {
        Intent intent = new Intent(this, cometoRegister.class);
        startActivity(intent);
    }


}