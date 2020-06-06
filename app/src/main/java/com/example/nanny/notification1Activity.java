package com.example.nanny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;

public class notification1Activity extends AppCompatActivity {

    private DatabaseReference databaseReference,databaseReference2;
    ListView listView;
    ArrayList<String> d = new ArrayList<>();
    ArrayList<String> uidn = new ArrayList<>();
    ArrayList<String> uidb = new ArrayList<>();
    ArrayList<String> namenanny = new ArrayList<>();

    String uidbaby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification1_1);

        databaseReference = FirebaseDatabase.getInstance().getReference("Requst");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Baby Data");

        listView = findViewById(R.id.gridview);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        final notification1Activity.MyAdapter adapter2 = new notification1Activity.MyAdapter(this,d);
        listView.setAdapter(adapter2);
//        final notification1Activity.MyAdapter adapter = new notification1Activity.MyAdapter(this,d);
//        listView.setAdapter(adapter);

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if(childSnapshot.child("mail_b").getValue(String.class).equals(user.getEmail())) {
                        uidbaby = childSnapshot.child("uid_b").getValue(String.class);
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
                if(dataSnapshot.child("baby_uid").getValue(String.class).equals(uidbaby)){
                    d.add(dataSnapshot.child("datetime").getValue(String.class));
                    uidn.add(dataSnapshot.child("nanny_uid").getValue(String.class));
                    uidb.add(dataSnapshot.child("baby_uid").getValue(String.class));
                    namenanny.add(dataSnapshot.child("namenanny").getValue(String.class));
                    adapter2.notifyDataSetChanged();
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), itemnoti2.class);
                intent.putExtra("nanny_uid", uidn.get(position));
                intent.putExtra("baby_uid", uidb.get(position));
                intent.putExtra("namenanny", namenanny.get(position));
                startActivity(intent);
            }
        });


    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> dt;
        MyAdapter(Context c,ArrayList<String> dt){
            super(c,R.layout.test3,R.id.date,dt);
            this.context = c;
            this.dt = dt;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.test3,parent,false);
            TextView dati = row.findViewById(R.id.date);

            dati.setText(dt.get(position));

            return row;
        }

    }






    public void tojoblist (View view){
        Intent intent = new Intent(this,joblistActivity.class);
        startActivity(intent);
    }

    public void tohomepage(View view){
        Intent intent = new Intent(this, homeActivity1.class);
        startActivity(intent);
    }

    public void topersonpage(View view){
        Intent intent = new Intent(this, personActivity.class);
        startActivity(intent);
    }


}
