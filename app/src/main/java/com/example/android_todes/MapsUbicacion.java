package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
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
import android.widget.TextView;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.android_todes.databinding.ActivityMapsUbicacionBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MapsUbicacion extends AppCompatActivity {

    private GoogleMap mMap;

    TextView tvMensaje;
    Button btnMa;
    RecyclerView mRecycler;

    Button btnMuestraBottomSheet;


    // Minimo tiempo para updates en Milisegundos
    private static final long MIN_TIME = 10000; // 10 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ubicacion);

        mRecycler = findViewById(R.id.recyclerViewSingle);

        btnMuestraBottomSheet = findViewById(R.id.idBtnShowBottomSheet);

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
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.phone);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.phone:
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:3107048069"));
                        int result = ContextCompat.checkSelfPermission(MapsUbicacion.this, Manifest.permission.CALL_PHONE);
                        if (result == PackageManager.PERMISSION_GRANTED) {
                            startActivity(intent);
                        } else {
                            requestForCallPermission();
                        }
                }
                return false;
            }
        });








        btnMa=findViewById(R.id.btnMap);

        tvMensaje = findViewById(R.id.tvMensaje);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

        } else {
            iniciarLocalizacion();
        }


    }
    public void requestForCallPermission ()
    {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE))
        {
        }

    }

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


    public void RegresoCategorias (View view){
        Intent pasaCategorias= new Intent(MapsUbicacion.this,categoriasIncidencia.class);
        startActivity(pasaCategorias);

    }
}