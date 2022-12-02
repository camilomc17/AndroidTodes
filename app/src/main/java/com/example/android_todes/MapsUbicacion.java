package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MapsUbicacion extends AppCompatActivity {

    private GoogleMap mMap;

    TextView tvMensaje;
   // Button btnRegresarUbi;
    RecyclerView mRecycler;
    TextView dir;
    Button btnMuestraBottomSheet;
    EditText  texDiecc;
    Button contiFormu;



    // Minimo tiempo para updates en Milisegundos
    private static final long MIN_TIME = 10000; // 10 segundos



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ubicacion);



//btnRegresarUbi=findViewById(R.id.btnRegresoUbica);
tvMensaje=findViewById(R.id.tvMensaje);
contiFormu=findViewById(R.id.butIrFormulario);

        dir=findViewById(R.id.textDier);
        Bundle recibeDatos=getIntent().getExtras();
        String info=recibeDatos.getString("Keydireccion");
        dir.setText(info);

/*btnRegresarUbi.setOnClickListener(new View.OnClickListener(){

    public void onClick(View view){
        Intent intent = new Intent(MapsUbicacion.this,MiUbicacion.class);
        startActivity(intent);
    }
     });*/

        btnMuestraBottomSheet=findViewById(R.id.idBtnShowBottomSheet);

        contiFormu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Bundle enviaDatos= new Bundle();
                enviaDatos.putString("KeyD",dir.getText().toString());
                Intent intent = new Intent(MapsUbicacion.this,FormActivityDenuncia.class);
                intent.putExtras(enviaDatos);
                startActivity(intent);
            }
        });
        mRecycler = findViewById(R.id.recyclerViewSingle);
        texDiecc = findViewById(R.id.editTextDireccion);


        mRecycler = findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
//      Query query = mFirestore.collection("pet").whereEqualTo("id_user", mAuth.getCurrentUser().getUid());

        btnMuestraBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog = new BottomSheetDialog(MapsUbicacion.this);


                View vista = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_dialog, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);
                dialog.show();


                }


        });
        tvMensaje = findViewById(R.id.tvMensaje);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

        } else {
            iniciarLocalizacion();
        }

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation_incidencia);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.phone_admin:
                    String cellAdmin = "+57-";
                    Intent llamada_admin = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", cellAdmin, null));
                    startActivity(llamada_admin);
                    return true;
                case R.id.phone_ambulance:
                    String cellAmbulance = "+57-23";
                    Intent llamada_ambulance = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", cellAmbulance, null));
                    startActivity(llamada_ambulance);
                    return true;
                case R.id.phone_emergency:
                    String cellEmergency = "+57-911";
                    Intent llamada_emergency = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", cellEmergency, null));
                    startActivity(llamada_emergency);
                    return true;
            }

            return false;
        }
    };

  /*  public void requestForCallPermission ()
    {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE))
        {
        }

    }*/

    private void iniciarLocalizacion() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Localizacion local = new Localizacion();

        local.setMapsUbicacion(this, tvMensaje);

        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!gpsEnabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, 0, local);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, 0, local);

        tvMensaje.setText("Localizacion agregada");
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                iniciarLocalizacion();
                return;
            }
        }
    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */




    }

