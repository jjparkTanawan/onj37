package com.example.nanny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class itemjoblist1 extends AppCompatActivity {

    private DatabaseReference databaseReference,databaseReference2,databaseReference3;
    String uidb,uidn,namebaby,namenanny;
    private TextView name,citizen,age,phone,line;
    private Button review,endjob;
    private TextView location;
    private ImageView image;
    private CheckBox c1,c2,c3,c4,c5,c6,c7;
    private CheckBox w1,w2,w3,w4;

    String uidbabyc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seedata_job1);

        databaseReference = FirebaseDatabase.getInstance().getReference("Nanny Data");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Baby Data");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("Record");

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        citizen = findViewById(R.id.citizen);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        line = findViewById(R.id.line);
        location = findViewById(R.id.location);

        c1 = findViewById(R.id.checkBox1);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);
        c4 = findViewById(R.id.checkBox4);
        c5 = findViewById(R.id.checkBox5);
        c6 = findViewById(R.id.checkBox6);
        c7 = findViewById(R.id.checkBox7);

        w1 = findViewById(R.id.checkBox8);
        w2 = findViewById(R.id.checkBox9);
        w3 = findViewById(R.id.checkBox10);
        w4 = findViewById(R.id.checkBox11);

        review = findViewById(R.id.review);
        endjob = findViewById(R.id.endjob);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        Intent intent = getIntent();
        uidb=(intent.getStringExtra("baby_uid"));
        uidn = (intent.getStringExtra("nanny_uid"));
        namenanny = (intent.getStringExtra("namenanny"));

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    if(dataSnapshot1.child("mail_b").getValue(String.class).equals(user.getEmail())){
                        uidbabyc = dataSnapshot1.child("uid_b").getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.child("nanny_uid").getValue().equals(uidn)) {
                    Picasso.get().load(dataSnapshot.child("image_n").getValue().toString()).into(image);
                    name.setText(dataSnapshot.child("name_n").getValue(String.class));
                    citizen.setText(dataSnapshot.child("citizen_n").getValue(String.class));
                    age.setText(dataSnapshot.child("age_n").getValue(String.class));
                    phone.setText(dataSnapshot.child("phone_n").getValue(String.class));
                    line.setText(dataSnapshot.child("line_n").getValue(String.class));
                    location.setText(dataSnapshot.child("location_n").child("location").getValue(String.class));

                    if(dataSnapshot.child("condition_n").child("c1").getValue(String.class)!=null){
                        c1.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_n").child("c2").getValue(String.class)!=null){
                        c2.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_n").child("c3").getValue(String.class)!=null){
                        c3.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_n").child("c4").getValue(String.class)!=null){
                        c4.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_n").child("c5").getValue(String.class)!=null){
                        c5.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_n").child("c6").getValue(String.class)!=null){
                        c6.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_n").child("c7").getValue(String.class)!=null){
                        c7.setChecked(true);
                    }

                    if(dataSnapshot.child("welfare_n").child("w1").getValue(String.class)!=null){
                        w1.setChecked(true);
                    }
                    if(dataSnapshot.child("welfare_n").child("w2").getValue(String.class)!=null){
                        w2.setChecked(true);
                    }
                    if(dataSnapshot.child("welfare_n").child("w3").getValue(String.class)!=null){
                        w3.setChecked(true);
                    }
                    if(dataSnapshot.child("welfare_n").child("w4").getValue(String.class)!=null){
                        w4.setChecked(true);
                    }
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(itemjoblist1.this, reviewActivity.class);
                startActivity(intent1);
            }
        });

        endjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            if(dataSnapshot1.child("baby_uid").getValue(String.class).equals(uidbabyc)){
                                if(dataSnapshot1.child("nanny_uid").getValue(String.class).equals(uidn)){
                                    dataSnapshot1.getRef().removeValue();
                                    Intent intent = new Intent(itemjoblist1.this, joblistActivity.class);
                                    startActivity(intent);
                                }}}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
