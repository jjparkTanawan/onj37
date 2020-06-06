package com.example.nanny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listreviewActivity extends AppCompatActivity {

    private DatabaseReference databaseReference,databaseReference2;

    ListView listView;
    String nanny_uid;
    Float sc;


    ArrayList<String> namebaby = new ArrayList<>();
    ArrayList<Float> score = new ArrayList<>();
    ArrayList<String> detail = new ArrayList<>();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listreview);

        listView = findViewById(R.id.gridview);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Review");

        final listreviewActivity.MyAdapter adapter = new listreviewActivity.MyAdapter(this,namebaby,detail,score);
        listView.setAdapter(adapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    if(childSnapshot.child("nanny_uid").getValue(String.class).equals(getIntent().getStringExtra("nid"))) {
                    namebaby.add(childSnapshot.child("namebaby").getValue(String.class));
                    score.add(childSnapshot.child("score").getValue(Float.class));
//                            score.add(" " + sc);
                    detail.add(childSnapshot.child("review_detail").getValue(String.class));
                    adapter.notifyDataSetChanged();
//                    Toast.makeText(getApplicationContext(),"Error"+getIntent().getStringExtra("nid"),Toast.LENGTH_SHORT).show();

                    }}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
//                    if(childSnapshot.child("nanny_uid").equals(nanny_uid)) {
//                            namebaby.add(childSnapshot.child("namebaby").getValue(String.class));
//                            score.add(childSnapshot.child("score").getValue(Float.class));
////                            score.add(" " + sc);
//                            detail.add(childSnapshot.child("review_detail").getValue(String.class));
//                            adapter.notifyDataSetChanged();
//               }}
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }


    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> name;
        ArrayList<String> detail;
        ArrayList<Float> score;
        MyAdapter(Context c,ArrayList<String> name,ArrayList<String> detail,ArrayList<Float> s){
            super(c,R.layout.itemreview,R.id.namebaby,name);
            this.context = c;
            this.name = name;
            this.detail = detail;
            this.score = s;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.itemreview,parent,false);
            TextView name = row.findViewById(R.id.namebaby);
            TextView detail2 = row.findViewById(R.id.tail);
            RatingBar scor = row.findViewById(R.id.RatingBar);

            name.setText(namebaby.get(position));
            detail2.setText(detail.get(position));
            scor.setRating(score.get(position));

            return row;
        }
    }


    public void setIDN(String nuid){
        nanny_uid=nuid;
    }
}




