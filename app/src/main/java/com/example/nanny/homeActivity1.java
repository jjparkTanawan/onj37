package com.example.nanny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.text.DecimalFormat;
import java.util.ArrayList;


public class homeActivity1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference databaseReference,databaseReference2;
    GridView listView;
    Spinner spinner;

    ArrayList<String> nannyuid = new ArrayList<>();
    ArrayList<String> nid = new ArrayList<>();
    ArrayList<String> n = new ArrayList<>();
    ArrayList<String> c = new ArrayList<>();
    ArrayList<String> i = new ArrayList<>();
    ArrayList<String> a = new ArrayList<>();
    ArrayList<String> p = new ArrayList<>();
    ArrayList<String> l = new ArrayList<>();
    ArrayList<String> lo = new ArrayList<>();
    ArrayList<String> condition = new ArrayList<>();
    ArrayAdapter<String> carArrayAdapter;
//    ArrayList<String> uid = new ArrayList<>();
    String uid;
    String name;
    Double latib,longtib;

    Double dis;
    ArrayList<String> dis2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_1);

        databaseReference = FirebaseDatabase.getInstance().getReference("Nanny Data");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Baby Data");

        listView = (GridView)findViewById(R.id.gridview);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adaptersp = ArrayAdapter.createFromResource(this,R.array.club,android.R.layout.simple_spinner_item);
        adaptersp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptersp);
        spinner.setOnItemSelectedListener(this);


        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        final MyAdapter adapter = new MyAdapter(this,n,p,i);
        listView.setAdapter(adapter);


        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if(childSnapshot.child("mail_b").getValue().equals(user.getEmail())) {
                        uid = childSnapshot.child("uid_b").getValue(String.class);
                        name = childSnapshot.child("name_b").getValue(String.class);
                        latib = childSnapshot.child("location_b").child("latitude").getValue(Double.class);
                        longtib = childSnapshot.child("location_b").child("longtidu").getValue(Double.class);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        databaseReference2.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                        uid = dataSnapshot.getKey();
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

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        nid.add(dataSnapshot.getKey());
                        nannyuid.add(dataSnapshot.child("nanny_uid").getValue(String.class));
                        n.add(dataSnapshot.child("name_n").getValue(String.class));
                        c.add(dataSnapshot.child("citizen_n").getValue(String.class));
                        i.add(dataSnapshot.child("image_n").getValue(String.class));
                        a.add(dataSnapshot.child("age_n").getValue(String.class));
                        p.add(dataSnapshot.child("phone_n").getValue(String.class));
                        l.add(dataSnapshot.child("line_n").getValue(String.class));
                        lo.add(dataSnapshot.child("location_n").child("location").getValue(String.class));
                        condition.add(dataSnapshot.child("condition_n").child("c1").getValue(String.class));
                        Double lat = dataSnapshot.child("location_n").child("latitude").getValue(Double.class);
                        Double lon = dataSnapshot.child("location_n").child("longtidu").getValue(Double.class);

                        DecimalFormat DF = new DecimalFormat("#.##");
                        Double sum =  Double.valueOf(DF.format(Math.sqrt(Math.pow(longtib-lon,2)+Math.pow(latib-lat,2))*100));
                        dis2.add(sum+"");
                        adapter.notifyDataSetChanged();
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
                Intent intent = new Intent(getApplicationContext(),seedetail.class);
                intent.putExtra("nanny_uid",nannyuid.get(position));
                intent.putExtra("nid",nid.get(position));
                intent.putExtra("namenanny",n.get(position));
//                intent.putExtra("citizen",c.get(position));
//                intent.putExtra("age",a.get(position));
//                intent.putExtra("phone",p.get(position));
//                intent.putExtra("line",l.get(position));
//                intent.putExtra("location",lo.get(position));
//                intent.putExtra("c1",condition.get(position));

                intent.putExtra("uid",uid.toString());
                intent.putExtra("namebaby",name.toString());
                startActivity(intent);
            }
        });

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
       if(parent.getItemAtPosition(position).equals("คะแนนความนิยม")){
           Intent intent3 = new Intent(this, homeActivity3.class);
           startActivity(intent3);
       }else
       if(parent.getItemAtPosition(position).equals("ระยะทาง")){
            Intent intent2 = new Intent(this, homeActivity2.class);
            startActivity(intent2);
       }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        ArrayList<String> names;
        ArrayList<String> citizens;
        ArrayList<String> imgs;

        MyAdapter(Context c,ArrayList<String> names,ArrayList<String> citizens,ArrayList<String> imgs){
            super(c,R.layout.user_info,R.id.userinfo,names);
            this.context = c;
            this.names = names;
            this.citizens = citizens;
            this.imgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.user_info,parent,false);
            ImageView images = row.findViewById(R.id.nannuimage);
            TextView name = row.findViewById(R.id.userinfo);
            TextView citizen = row.findViewById(R.id.userinfo2);

            Picasso.get().load(imgs.get(position)).into(images);
            name.setText(names.get(position));
            citizen.setText("โทร : "+citizens.get(position));

            return row;
        }
    }


    public void topersonpage(View view){
        Intent intent = new Intent(this, personActivity.class);
        startActivity(intent);
    }

    public void tononticication(View view){
        Intent intent = new Intent(this, notification1Activity.class);
        startActivity(intent);
    }

    public void tojoblist (View view){
        Intent intent = new Intent(this, joblistActivity.class);
        startActivity(intent);
    }
}
