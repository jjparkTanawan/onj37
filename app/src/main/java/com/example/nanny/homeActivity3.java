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
import java.util.Collections;
import java.util.HashMap;


public class homeActivity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference databaseReference,databaseReference2,databaseReference3;
    GridView listView;
    Spinner spinner;

    ArrayList<String> phoned = new ArrayList<>();
    ArrayList<String> imaged = new ArrayList<>();
    ArrayList<String> disd = new ArrayList<>();


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

    ArrayList<Double> dis = new ArrayList<>();
    ArrayList<String> dis2 = new ArrayList<>();

    ArrayList<Float> score = new ArrayList<>();
    HashMap<String,Float> floatHashMap = new HashMap<>();
    HashMap<String,Integer> integerHashMap =  new HashMap<>();

    int sumscore;
    ArrayList<String> score_av = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_1);

        databaseReference = FirebaseDatabase.getInstance().getReference("Nanny Data");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Baby Data");
        databaseReference3 = FirebaseDatabase.getInstance().getReference().child("Review");

        listView = (GridView)findViewById(R.id.gridview);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adaptersp = ArrayAdapter.createFromResource(this,R.array.club3,android.R.layout.simple_spinner_item);
        adaptersp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptersp);
        spinner.setOnItemSelectedListener(this);


        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        final MyAdapter adapter = new MyAdapter(this,score_av,p,i);
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


        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                        score.add(childSnapshot.child("score").getValue(Float.class));
                        if(floatHashMap.get(childSnapshot.child("nanny_uid").getValue().toString())!=null){
                            Float number = floatHashMap.get(childSnapshot.child("nanny_uid").getValue().toString());
                            floatHashMap.put(childSnapshot.child("nanny_uid").getValue().toString(), childSnapshot.child("score").getValue(Float.class)+number);
                            Integer intnumber = integerHashMap.get(childSnapshot.child("nanny_uid").getValue().toString());
                            integerHashMap.put(childSnapshot.child("nanny_uid").getValue().toString(), intnumber+1);
                        }else {
                            floatHashMap.put(childSnapshot.child("nanny_uid").getValue().toString(), childSnapshot.child("score").getValue(Float.class));
                            integerHashMap.put(childSnapshot.child("nanny_uid").getValue().toString(), 1);
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


                if(floatHashMap.get(nannyuid.get(nannyuid.size()-1))!=null) {
                    score_av.add("คะแนนรีวิว : " + floatHashMap.get(nannyuid.get(nannyuid.size() - 1)) / integerHashMap.get(nannyuid.get(nannyuid.size() - 1)) * 1.0 +"/5");
                }else {
                    score_av.add("คะแนนรีวิว : " + 0 +"/5");
                }
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
        if(parent.getItemAtPosition(position).equals("โปรไฟล์ทั้งหมด")){
            Intent intent = new Intent(this, homeActivity1.class);
            startActivity(intent);
        }else
        if(parent.getItemAtPosition(position).equals("ระยะทาง")){
            Intent intent = new Intent(this, homeActivity2.class);
            startActivity(intent);
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
