package com.example.nanny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class seedetail extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView name,citizen,age,phone,line;
    private TextView seereview;
    private Button requst;
    private TextView location;
    private ImageView image;
    private CheckBox c1,c2,c3,c4,c5,c6,c7;
    private CheckBox w1,w2,w3,w4;
    String nid,uid,namebaby,namenanny,nannyuid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seedata_listdata);


        databaseReference = FirebaseDatabase.getInstance().getReference("Nanny Data");

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

        requst = findViewById(R.id.requst);
        seereview = findViewById(R.id.seereview);

        Intent intent = getIntent();
        nannyuid = (intent.getStringExtra("nanny_uid"));
        nid = intent.getStringExtra("nid");
        uid = intent.getStringExtra("uid");
        namebaby = intent.getStringExtra("namebaby");
        namenanny = intent.getStringExtra("namenanny");



        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.child("nanny_uid").getValue().equals(nannyuid)) {
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

//                    c1.setEnabled(false);
//                    c2.setEnabled(false);
//                    c3.setEnabled(false);
//                    c4.setEnabled(false);
//                    c5.setEnabled(false);
//                    c6.setEnabled(false);
//                    c7.setEnabled(false);
//
//                    w1.setEnabled(false);
//                    w2.setEnabled(false);
//                    w3.setEnabled(false);
//                    w4.setEnabled(false);

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

        seereview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senddata();

                Intent intent = new Intent(seedetail.this, listreviewActivity.class);
                intent.putExtra("nid",nid);
                startActivity(intent);
            }
        });


        requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


    }

    private void senddata(){
        listreviewActivity list = new listreviewActivity();
        list.setIDN(nid);
    }

    private void openDialog() {
        DialogBooking dialogBooking = new DialogBooking();
        dialogBooking.setIDB(uid);
        dialogBooking.setname(namebaby);
        dialogBooking.setnamen(namenanny);
        dialogBooking.setID(nid);
        dialogBooking.show(getSupportFragmentManager(),"example dialog");
    }
}
