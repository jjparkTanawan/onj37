package com.example.nanny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
import com.google.firebase.database.ValueEventListener;

public class reviewActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,databaseReference2,databaseReference3;
    private RatingBar ratingBar;
    private TextView scoretext;
    private EditText reviewe;
    private Button save;
    private Float score;
    private Review review;

    private String uidbaby,namebaby,uidnanny,dtail;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        ratingBar = findViewById(R.id.RatingBar);
        scoretext = findViewById(R.id.scoretext);
        reviewe = findViewById(R.id.review);
        save = findViewById(R.id.savereview);

        databaseReference = FirebaseDatabase.getInstance().getReference("Baby Data");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Record");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("Review");

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if (childSnapshot.child("mail_b").getValue().equals(user.getEmail())) {
                        uidbaby = childSnapshot.child("uid_b").getValue(String.class);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if(childSnapshot.child("baby_uid").getValue().equals(uidbaby)) {
                    namebaby = childSnapshot.child("namebaby").getValue(String.class);
                    uidnanny = childSnapshot.child("nanny_uid").getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                scoretext.setText("คะแนน : "+rating);
                                score = rating;

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtail = reviewe.getText().toString();
                review = new Review(uidnanny,uidbaby,namebaby,score,dtail);
                databaseReference3.child(databaseReference3.push().getKey()).setValue(review);
                Intent intent1 = new Intent(reviewActivity.this, joblistActivity.class);
                startActivity(intent1);
            }
        });


    }
}
