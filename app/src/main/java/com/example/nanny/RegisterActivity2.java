package com.example.nanny;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterActivity2 extends AppCompatActivity {

    private String Member,nameN,citizenN,ageN,mailN,passN,phoneN,lineN,saveCurrentDate,saveCurrentTime,locationN;
    private double lati,longti;
    private EditText citizen,age,mail,pass,phone,line,name;
    private Button mchooseBtn,next;
    private ImageView mImageView;
    private static final int GalleryPick=1;
    private Uri ImageUri;
    private String privateRandomKey,dowloadImageUri;
    private StorageReference NannyImagesRef;
    private DatabaseReference NannyRef;
    private String c1n,c2n,c3n,c4n,c5n,c6n,c7n;
    private CheckBox c1,c2,c3,c4,c5,c6,c7;
    private String w1n,w2n,w3n,w4n;
    private CheckBox w1,w2,w3,w4;

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private TextView textLatlong,textAddress2;
    private ResultReceiver resultReceiver;
    private FirebaseAuth fAuth;
    Nanny member;
    Condition condition_n;
    Welfere welfare;
    location location;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_2);

//        Member = getIntent().getExtras().get("Member").toString();
        NannyRef = FirebaseDatabase.getInstance().getReference("member");
        NannyImagesRef = FirebaseStorage.getInstance().getReference().child("Images");
        NannyRef = FirebaseDatabase.getInstance().getReference().child("Nanny Data");
        fAuth = FirebaseAuth.getInstance();

        name=(EditText)findViewById(R.id.name2);
        citizen=(EditText)findViewById(R.id.citizen2);
        age = (EditText)findViewById(R.id.age2);
        mail=(EditText)findViewById(R.id.mail2);
        pass=(EditText)findViewById(R.id.pass2);
        phone=(EditText)findViewById(R.id.phone2);
        line=(EditText)findViewById(R.id.line2);
        next = (Button)findViewById(R.id.update2);
        mchooseBtn = (Button)findViewById(R.id.choose_image_btn);
        mImageView = (ImageView)findViewById(R.id.image_view);

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

//        textLatlong = findViewById(R.id.Address2);
        textAddress2 = findViewById(R.id.location);
        resultReceiver = new RegisterActivity2.AddressResultReceiver(new Handler());


        mchooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateUserData();

            }
        });

        findViewById(R.id.buttonGetCurrentLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RegisterActivity2.this,new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION_PERMISSION);
                }else {
                    getCurrentLocation();
                }
            }
        });


    }

    private void getCurrentLocation(){
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(RegisterActivity2.this).requestLocationUpdates(locationRequest,new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                LocationServices.getFusedLocationProviderClient(RegisterActivity2.this).removeLocationUpdates(this);
                if(locationResult != null && locationResult.getLocations().size()>0){
                    int lastesLocation = locationResult.getLocations().size()-1;
                    double latitude = locationResult.getLocations().get(lastesLocation).getLatitude();
                    double longitude = locationResult.getLocations().get(lastesLocation).getLongitude();

                    lati = latitude;
                    longti = longitude;
                    //แสดงตำแหน่งlatitude&longitudeหน้าUI
//                    textLatlong.setText(String.format("Latitude: %s\nLongitude: %s",latitude,longitude));

                    Location location = new Location("providerNA");
                    location.setLatitude(latitude);
                    location.setLongitude(longitude);
                    fetchAddressFromLatLong(location);
                }
            }
        }, Looper.getMainLooper());


    }

    private void fetchAddressFromLatLong(Location location){
        Intent intent = new Intent(this,FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER,resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA,location);
        startService(intent);
    }

    private class AddressResultReceiver extends ResultReceiver{
        AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if(resultCode==Constants.SUCCESS_RESULT){
                //แสดงตำแหน่งlocationหน้า UI
                textAddress2.setText(resultData.getString(Constants.RESULT_DATA_KEY));
            }else {
                Toast.makeText(RegisterActivity2.this,resultData.getString(Constants.RESULT_DATA_KEY),Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void onRequstPermissionResult(int requestCode, String[] permission, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permission,grantResults);
        if(requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }else {
                Toast.makeText(this,"permission",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GalleryPick && data != null && data.getData() != null) {
            //set image to image view
            ImageUri = data.getData();
            mImageView.setImageURI(ImageUri);
        }
    }


    private void ValidateUserData() {
        nameN = name.getText().toString();
        citizenN = citizen.getText().toString();
        ageN = age.getText().toString();
        mailN = mail.getText().toString();
        passN = pass.getText().toString();
        phoneN = phone.getText().toString();
        lineN = line.getText().toString();
        locationN = textAddress2.getText().toString();


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

        String email = mail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (email.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(),"อีเมล์ถูกต้อง",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "อีเมล์ไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(mailN)&& !TextUtils.isEmpty(passN) && !TextUtils.isEmpty(citizenN) &&
                !TextUtils.isEmpty(phoneN)){

            if(citizen.length()<13){
                citizen.setError("กรุณาใส่รหัสบัตรประชาชนใหม่");
                return;
            }else if(pass.length()<6){
                pass.setError("กรุณาใส่รหัสผ่านมากกว่า 6 ตัว");
                return;
            }else if(phone.length()<10){
                phone.setError("กรุณากรอกใหม่");
                return;
            }
        }


        if(ImageUri == null){
            Toast.makeText(this,"กรุณาใส่รูปภาพ",Toast.LENGTH_SHORT).show();
        }else
            StoreImageInformation();


    }

    private void StoreImageInformation() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentData = new SimpleDateFormat("MMMM dd,yyyy");
        saveCurrentDate = currentData.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        privateRandomKey = saveCurrentDate+saveCurrentTime;

        if(!TextUtils.isEmpty(mailN)&& !TextUtils.isEmpty(passN) && !TextUtils.isEmpty(citizenN) &&
                !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(ageN)) {

            final StorageReference filePath = NannyImagesRef.child(ImageUri.getLastPathSegment() + privateRandomKey + ".jpg");

            final UploadTask uploadTask = filePath.putFile(ImageUri);


            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String message = e.toString();
                    Toast.makeText(RegisterActivity2.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            dowloadImageUri = String.valueOf(uri);
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
//                                Toast.makeText(addNewprofile.this, "อัปโหลดรูปภาพสำเร็จ", Toast.LENGTH_SHORT).show();
                                savetofirebase();
                            }
                        }
                    });
                }
            });
        }
    }


    private void savetofirebase(){
        String id = NannyRef.push().getKey();
        condition_n = new Condition(c1n,c2n,c3n,c4n,c5n,c6n,c7n);
        welfare = new Welfere(w1n,w2n,w3n,w4n);
        location = new location(lati,longti,locationN);
        member = new Nanny(id,nameN,citizenN,ageN,mailN,passN,phoneN,lineN,dowloadImageUri,condition_n,welfare,location);
        NannyRef.child(id).setValue(member);

        next2(mailN,passN);
    }

    //authen
    public void next2(String mail,String pass) {

        if (!TextUtils.isEmpty(mailN) && !TextUtils.isEmpty(passN) && !TextUtils.isEmpty(citizenN) &&
                !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(ageN)) {

            fAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
//                        Toast.makeText(addNewprofile.this, "User Authen", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), modeActivity2.class));

                    } else {
                        Toast.makeText(RegisterActivity2.this, "Error..!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }


    public void tologin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
