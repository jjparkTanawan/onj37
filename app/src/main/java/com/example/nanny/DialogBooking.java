package com.example.nanny;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class DialogBooking extends AppCompatDialogFragment {

    private EditText date,time;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,databaseReference2;
    private Request request;
    String baby_uid;
    String nanny_uid;
    String name_baby;
    String name_nanny;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.booking,null);

        builder.setView(view).setTitle("จองกิจกรรมการดูแล").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String datee = date.getText().toString();
                String timee = time.getText().toString();

                firebaseAuth = FirebaseAuth.getInstance();

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Requst");

                final FirebaseUser user = firebaseAuth.getCurrentUser();
//                baby_uid = user.getUid();
                request = new Request(false,baby_uid,nanny_uid,name_baby,name_nanny,"ขอรับบริการวันที่ "+datee+" เวลา "+timee);
                databaseReference.child(databaseReference.push().getKey()).setValue(request);
//                databaseReference.child(databaseReference.push().getKey()).setValue(request);

            }
        });


        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);


        return builder.create();
    }

    public void setID(String nannyuid){
        nanny_uid=nannyuid;
    }

    public void setname(String namebaby){
        name_baby=namebaby;
    }

    public void setnamen(String namenanny){
        name_nanny=namenanny;
    }

    public void setIDB(String babyuid){
        baby_uid=babyuid;
    }

}
