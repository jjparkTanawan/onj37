package com.example.nanny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.ArrayList;

public class notificationActivity2 extends AppCompatActivity {

    private DatabaseReference databaseReference,databaseReference2,databaseReference3,databaseReferencegetuid;
    ListView listView;

    String uidnanny;
    ArrayList<String> nid = new ArrayList<>();
    ArrayList<String> d = new ArrayList<>();
    ArrayList<String> uidn = new ArrayList<>();
    ArrayList<String> uidb = new ArrayList<>();
    ArrayList<String> namenanny = new ArrayList<>();
    String uidbaby;

    String age ;
    String gender ;
    String line ;
    String mail ;
    String name_b ;
    String names_b ;
    String phone;
    String relative;
    String undis ;

    ArrayList<String> lo = new ArrayList<>();
    ArrayList<String> condition = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification2_1);

        databaseReference3 = FirebaseDatabase.getInstance().getReference("Baby Data");
        databaseReference = FirebaseDatabase.getInstance().getReference("Requst");
        databaseReferencegetuid = FirebaseDatabase.getInstance().getReference("Requst");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Nanny Data");

        listView = findViewById(R.id.listview);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        final notificationActivity2.MyAdapter adapter = new notificationActivity2.MyAdapter(this,d);
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


        databaseReferencegetuid.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if((dataSnapshot.child("nanny_uid").getValue(String.class)).equals(uidnanny)) {
                    uidbaby = dataSnapshot.child("baby_uid").getValue(String.class);
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



        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if((dataSnapshot.child("nanny_uid").getValue(String.class)).equals(uidnanny)) {
                    d.add(dataSnapshot.child("datetime").getValue(String.class));
                    uidn.add(dataSnapshot.child("nanny_uid").getValue(String.class));
                    uidb.add(dataSnapshot.child("baby_uid").getValue(String.class));
                    namenanny.add(dataSnapshot.child("namenanny").getValue(String.class));
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
                    Intent intent = new Intent(getApplicationContext(), itemnoti.class);
//                    intent.putExtra("date", d.get(position));
                    intent.putExtra("nanny_uid", uidn.get(position));
                    intent.putExtra("namenanny", namenanny.get(position));

//                    intent.putExtra("baby_uid", uidb.get(position));
                    intent.putExtra("baby_uid", uidb.get(position));
//                    intent.putExtra("gender_b", gender.get(position));
//                    intent.putExtra("line_b", line.get(position));
//                    intent.putExtra("name_b", age.toString());
//                    intent.putExtra("nameb_b", names_b.get(position));
//                    intent.putExtra("phone_b", phone.get(position));
//                    intent.putExtra("relative_b", relative.get(position));
//                    intent.putExtra("undis_b", undis.get(position));
                    startActivity(intent);
                }
            });


    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> dt;
        MyAdapter(Context c,ArrayList<String> dt){
            super(c,R.layout.itemnoti,R.id.date,dt);
            this.context = c;
            this.dt = dt;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.itemnoti,parent,false);
            TextView dati = row.findViewById(R.id.date);

            dati.setText(dt.get(position));

            return row;
        }

    }

    public void tomodepage(View view){
        Intent intent = new Intent(this, modeActivity2.class);
        startActivity(intent);
    }

    public void tojoblist (View view){
        Intent intent = new Intent(this, joblistActivity2.class);
        startActivity(intent);
    }

    public void topersonpage(View view){
        Intent intent = new Intent(this, personActivity2.class);
        startActivity(intent);
    }
}
