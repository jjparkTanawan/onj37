package com.example.nanny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class editprofileActivity2 extends AppCompatActivity {

    private String Member,nameN,citizenN,ageN,mailN,passN,phoneN,lineN,saveCurrentDate,saveCurrentTime,locationN;
    private String c1n,c2n,c3n,c4n,c5n,c6n,c7n;
    private String w1n,w2n,w3n,w4n;
    private String uid;
    private String imag;
    private Double latib,longtib;

    private EditText editname,editcitizen,editage,editphone,editline;
    private Button save;
    private TextView location;
    private ImageView profilePicImageView;
    private CheckBox c1,c2,c3,c4,c5,c6,c7;
    private CheckBox w1,w2,w3,w4;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private static final String MEMBER = "Nanny Data";

    Condition condition_b;
    Welfere welfere_b;
    location location_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_2);

        editname = (EditText)findViewById(R.id.name2);
        editcitizen = (EditText)findViewById(R.id.citizen2);
        editage = (EditText)findViewById(R.id.age2);
        editphone = (EditText)findViewById(R.id.phone2);
        editline = (EditText)findViewById(R.id.line2);

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

        location = (TextView)findViewById(R.id.location);
        profilePicImageView = (ImageView)findViewById(R.id.image_view);

        save = (Button)findViewById(R.id.update2) ;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

         firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(MEMBER);


        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        final FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                        if(childSnapshot.child("mail_n").getValue().equals(user.getEmail())) {

                            mailN = childSnapshot.child("mail_n").getValue(String.class);
                            passN = childSnapshot.child("pass_n").getValue(String.class);
                            uid = childSnapshot.child("nanny_uid").getValue(String.class);
                            latib = childSnapshot.child("location_n").child("latitude").getValue(Double.class);
                            longtib = childSnapshot.child("location_n").child("longtidu").getValue(Double.class);
                            imag = childSnapshot.child("image_n").getValue(String.class);

                        Picasso.get().load(childSnapshot.child("image_n").getValue().toString()).into(profilePicImageView);

                        editname.setText(childSnapshot.child("name_n").getValue(String.class));
                        editcitizen.setText(childSnapshot.child("citizen_n").getValue(String.class));
                        editage.setText(childSnapshot.child("age_n").getValue(String.class));
                        editphone.setText(childSnapshot.child("phone_n").getValue(String.class));
                        editline.setText(childSnapshot.child("line_n").getValue(String.class));
                        location.setText(childSnapshot.child("location_n").child("location").getValue(String.class));

                        if(childSnapshot.child("condition_n").child("c1").getValue(String.class)!=null){
                            c1.setChecked(true);
                        }
                        if(childSnapshot.child("condition_n").child("c2").getValue(String.class)!=null){
                            c2.setChecked(true);
                        }
                        if(childSnapshot.child("condition_n").child("c3").getValue(String.class)!=null){
                            c3.setChecked(true);
                        }
                        if(childSnapshot.child("condition_n").child("c4").getValue(String.class)!=null){
                            c4.setChecked(true);
                        }
                        if(childSnapshot.child("condition_n").child("c5").getValue(String.class)!=null){
                            c5.setChecked(true);
                        }
                        if(childSnapshot.child("condition_n").child("c6").getValue(String.class)!=null){
                            c6.setChecked(true);
                        }
                        if(childSnapshot.child("condition_n").child("c7").getValue(String.class)!=null){
                            c7.setChecked(true);
                        }

                        if(childSnapshot.child("welfare_n").child("w1").getValue(String.class)!=null){
                            w1.setChecked(true);
                        }
                        if(childSnapshot.child("welfare_n").child("w2").getValue(String.class)!=null){
                            w2.setChecked(true);
                        }
                        if(childSnapshot.child("welfare_n").child("w3").getValue(String.class)!=null){
                            w3.setChecked(true);
                        }
                        if(childSnapshot.child("welfare_n").child("w4").getValue(String.class)!=null){
                            w4.setChecked(true);
                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameN = editname.getText().toString();
                citizenN = editcitizen.getText().toString();
                ageN = editage.getText().toString();
                phoneN = editphone.getText().toString();
                lineN = editline.getText().toString();
                locationN = location.getText().toString();

                if(c1.isChecked()){
                    c1n = c1.getText().toString();
                }else {

                }if(c2.isChecked()){
                    c2n = c2.getText().toString();
                }else {

                }if(c3.isChecked()){
                    c3n = c3.getText().toString();

                }else {

                }if(c4.isChecked()){
                    c4n = c4.getText().toString();

                }else {

                }if(c5.isChecked()){
                    c5n = c5.getText().toString();

                }else {

                }if(c6.isChecked()){
                    c6n = c6.getText().toString();

                }else {

                }if(c7.isChecked()){
                    c7n = c7.getText().toString();

                }

                if(w1.isChecked()){
                    w1n = w1.getText().toString();
                }else {

                }if(w2.isChecked()){
                    w2n = w2.getText().toString();
                }else {

                }if(w3.isChecked()){
                    w3n = w3.getText().toString();

                }else {

                }if(w4.isChecked()){
                    w4n = w4.getText().toString();

                }

                condition_b = new Condition(c1n,c2n,c3n,c4n,c5n,c6n,c7n);
                welfere_b = new Welfere(w1n,w2n,w3n,w4n);
                location_b = new location(latib,longtib,locationN);
                Nanny nanny = new Nanny(uid,nameN,citizenN,ageN,mailN,passN,phoneN,lineN,imag,condition_b,welfere_b,location_b);
                databaseReference.child(uid).setValue(nanny);
            }
        });
    }


}
