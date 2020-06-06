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


public class itemnoti extends AppCompatActivity {

    private DatabaseReference databaseReference,databaseReference2,databaseReference3;
    private TextView name_b,age_b,gender_b,undis_b,relative_b,nameb_b,phone_b,line_b,location_b;
    private Button start,cancel;
    private TextView location;
    private ImageView image;
    private CheckBox c1,c2,c3,c4,c5,c6,c7;
    private CheckBox w1,w2,w3,w4;
    private String uid,uidn,namebaby,namenanny;
    private Record record;

    String uidnannyc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seedata_itemnoti2);


        databaseReference = FirebaseDatabase.getInstance().getReference("Baby Data");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Requst");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("Nanny Data");


        image = findViewById(R.id.image_view);
        name_b = findViewById(R.id.babyname2);
        age_b = findViewById(R.id.age22);
        gender_b = findViewById(R.id.gender2);
        undis_b = findViewById(R.id.csds_1);
        relative_b = findViewById(R.id.relative_as2);
        nameb_b = findViewById(R.id.relative_n2);
        phone_b = findViewById(R.id.phone2);
        line_b = findViewById(R.id.line2);
        location_b = findViewById(R.id.textAddress);

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

        start = findViewById(R.id.startjob);
        cancel = findViewById(R.id.cancel2);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        Intent intent = getIntent();
        uid=(intent.getStringExtra("baby_uid"));
        uidn = (intent.getStringExtra("nanny_uid"));
        namenanny = (intent.getStringExtra("namenanny"));

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    if(dataSnapshot1.child("mail_n").getValue(String.class).equals(user.getEmail())){
                        uidnannyc = dataSnapshot1.child("nanny_uid").getValue(String.class);
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
                if(dataSnapshot.child("uid_b").getValue().equals(uid)) {

                    Picasso.get().load(dataSnapshot.child("image_b").getValue().toString()).into(image);

                    namebaby = (dataSnapshot.child("name_b").getValue(String.class));
                    name_b.setText(namebaby);
                    age_b.setText(dataSnapshot.child("age_b").getValue(String.class));
                    gender_b.setText(dataSnapshot.child("gender_b").getValue(String.class));
                    undis_b.setText(dataSnapshot.child("undis_b").getValue(String.class));
                    relative_b.setText(dataSnapshot.child("relative_b").getValue(String.class));
                    nameb_b.setText(dataSnapshot.child("nameb_b").getValue(String.class));
                    phone_b.setText(dataSnapshot.child("phone_b").getValue(String.class));
                    line_b.setText(dataSnapshot.child("line_b").getValue(String.class));
                    location_b.setText(dataSnapshot.child("location_b").child("location").getValue(String.class));

                    if(dataSnapshot.child("condition_b").child("c1").getValue(String.class)!=null){
                        c1.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_b").child("c2").getValue(String.class)!=null){
                        c2.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_b").child("c3").getValue(String.class)!=null){
                        c3.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_b").child("c4").getValue(String.class)!=null){
                        c4.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_b").child("c5").getValue(String.class)!=null){
                        c5.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_b").child("c6").getValue(String.class)!=null){
                        c6.setChecked(true);
                    }
                    if(dataSnapshot.child("condition_b").child("c7").getValue(String.class)!=null){
                        c7.setChecked(true);
                    }

                    if(dataSnapshot.child("welfare_b").child("w1").getValue(String.class)!=null){
                        w1.setChecked(true);
                    }
                    if(dataSnapshot.child("welfare_b").child("w2").getValue(String.class)!=null){
                        w2.setChecked(true);
                    }
                    if(dataSnapshot.child("welfare_b").child("w3").getValue(String.class)!=null){
                        w3.setChecked(true);
                    }
                    if(dataSnapshot.child("welfare_b").child("w4").getValue(String.class)!=null){
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

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Record");
                String id = databaseReference.push().getKey();
                record = new Record(uid,uidn,namebaby,namenanny);
                databaseReference.child(id).setValue(record);

//                Query q = databaseReference2.orderByChild("nanny_uid").equalTo(uidn);
                Query q1 = databaseReference2.orderByChild("baby_uid").equalTo(uid);

                q1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            if(dataSnapshot1.child("baby_uid").getValue(String.class).equals(uid)){
                                if(dataSnapshot1.child("nanny_uid").getValue(String.class).equals(uidnannyc)){
                            dataSnapshot1.getRef().removeValue();
                            Intent intent = new Intent(itemnoti.this, joblistActivity2.class);
                            startActivity(intent);
                        }}}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Query q = databaseReference2.orderByChild("nanny_uid").equalTo(uidn);
                Query q1 = databaseReference2.orderByChild("baby_uid").equalTo(uid);

                q1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            if(dataSnapshot1.child("baby_uid").getValue(String.class).equals(uid)){
                                if(dataSnapshot1.child("nanny_uid").getValue(String.class).equals(uidnannyc)){
                                    dataSnapshot1.getRef().removeValue();
                                    Intent intent = new Intent(itemnoti.this, notificationActivity2.class);
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
