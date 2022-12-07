package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ActivityMiCuenta extends AppCompatActivity {

    ImageView photo_User;
    Button btnUpdateUser;
    Button btnSubirfoto, btnDeletePhoto;
    LinearLayout linearLayoutImageBtn;
    EditText emailUser,PasswordUser;

    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;
    DatabaseReference Database;

    StorageReference storageReference;
    String storage_path  = "PhotosUsers/";  //nombre de la carpeta que contiene los archivos del storage *

    private static final int COD_SEL_STORAGE =200;
    private static final int COD_SEL_IMAGE =300;

    private Uri image_url;
    String photo = "photo";
    String idd;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);
        progressDialog = new ProgressDialog( this);

        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        Database = FirebaseDatabase.getInstance().getReference();
        idd = mAuth.getUid();

        getUser(idd);
        linearLayoutImageBtn = findViewById(R.id.imagesbtn);
        photo_User = findViewById(R.id.imageViewUser);
        btnSubirfoto = findViewById(R.id.btnSubirFoto);
        btnDeletePhoto = findViewById(R.id.btnEliminar);

        btnSubirfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPhoto();
            }
        });
        btnDeletePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Database.child("Usuarios").child(idd).child("photo").setValue("");
            }
        });

    }
    private void uploadPhoto() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,COD_SEL_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == COD_SEL_IMAGE) {
                image_url = data.getData();
                Subirfoto(image_url);
            }
        }
    }

    private void Subirfoto(Uri image_url) {
        progressDialog.setMessage("Actualizando Foto");
        progressDialog.show();
        String rute_storage_photo = storage_path+""+photo+""+mAuth.getUid()+""+idd; //String rute_storage_photo = storage_path+""+photo+""+mAuth.getUid()+""+idd;
        StorageReference reference = storageReference.child(rute_storage_photo);
        reference.putFile(image_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isSuccessful());
                if(uriTask.isSuccessful()){
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            /*String download_uri = uri.toString();
                            HashMap <String,Object> map = new HashMap<>();
                            map.put("photo",download_uri);
                            mfirestore.collection("Usuarios").document(idd).update(map);*/
                            String download_uri = uri.toString();
                            HashMap <String,Object> map = new HashMap<>();
                            map.put("photo",download_uri);
                            Database.child("Usuarios").child(idd).child("photo").setValue(download_uri);
                            Toast.makeText(ActivityMiCuenta.this,"Foto Actualizada", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    public void getUser(String id){
        /*mfirestore.collection("Usuarios").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String photoUser = documentSnapshot.getString("photo");

                try {
                    if(!photoUser.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(),"Cargando foto", Toast.LENGTH_SHORT);
                        toast.show();
                        Picasso.with(ActivityPerfil.this)
                                .load(photoUser)
                                .resize(150,150)
                                .into(photo_User);
                    }
                }catch (Exception e){
                    Log.v("Error","e"+e);
                }
            }
        });*/


        Database.child("Usuarios").child(idd).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String  photoUser = snapshot.child("photo").getValue().toString();
                    Toast toast = Toast.makeText(getApplicationContext(),"Cargando foto", Toast.LENGTH_SHORT);
                    toast.show();
                    Picasso.with(ActivityMiCuenta.this)
                            .load(photoUser)
                            .resize(150,150)
                            .into(photo_User);
                 }
                else{
                    /*
                    String  photoUser = snapshot.child("photo").getValue().toString();
                    Toast toast = Toast.makeText(getApplicationContext(),"Sin foto", Toast.LENGTH_SHORT);
                    toast.show();
                    Picasso.with(ActivityMiCuenta.this)
                        .load(photoUser)
                        .resize(150,150)
                        .into(photo_User); */
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}