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

public class joblistActivity2 extends AppCompatActivity {

    private DatabaseReference databaseReference,databaseReference2;
    ListView listView;
    String uidnanny;

//    private String uidn,uidb;
    private ArrayList<String> nameb = new ArrayList<>();
    private ArrayList<String> uidn = new ArrayList<>();
    private ArrayList<String> uidb = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joblist_2);

        listView = findViewById(R.id.gridview);

        databaseReference = FirebaseDatabase.getInstance().getReference("Record");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Nanny Data");

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();


        final joblistActivity2.MyAdapter adapter = new joblistActivity2.MyAdapter(this,nameb);
        listView.setAdapter(adapter);

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if(childSnapshot.child("mail_n").getValue().equals(user.getEmail())) {
                        uidnanny = childSnapshot.child("nanny_uid").getValue(String.class);
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
                if((dataSnapshot.child("nanny_uid").getValue(String.class)).equals(uidnanny)) {
                    uidb.add(dataSnapshot.child("baby_uid").getValue(String.class));
                    uidn.add(dataSnapshot.child("nanny_uid").getValue(String.class));
                    nameb.add(dataSnapshot.child("namebaby").getValue(String.class));
                    adapter.notifyDataSetChanged();
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
                Intent intent = new Intent(getApplicationContext(), itemjoblist2.class);
                intent.putExtra("nanny_uid", uidn.get(position));
                intent.putExtra("baby_uid", uidb.get(position));
                intent.putExtra("name", nameb.get(position));

                startActivity(intent);
            }
        });

    }


    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> dt;
        MyAdapter(Context c,ArrayList<String> dt){
            super(c,R.layout.itemjob2,R.id.name,dt);
            this.context = c;
            this.dt = dt;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.itemjob2,parent,false);
            TextView dati = row.findViewById(R.id.name);

            dati.setText(dt.get(position));

            return row;
        }

    }

    public void tomodepage(View view){
        Intent intent = new Intent(this, modeActivity2.class);
        startActivity(intent);
    }

    public void tononticication(View view){
        Intent intent = new Intent(this, notificationActivity2.class);
        startActivity(intent);
    }


    public void topersonpage(View view){
        Intent intent = new Intent(this, personActivity2.class);
        startActivity(intent);
    }


}
