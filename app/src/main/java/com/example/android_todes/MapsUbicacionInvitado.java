package com.example.android_todes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsUbicacionInvitado extends AppCompatActivity {

    private GoogleMap mMap;
    TextView latitud, longitud;
    TextView tvMensaje;
    // Button btnRegresarUbi;
    RecyclerView mRecycler;
    TextView dir;
    //Button btnMuestraBottomSheet;
    EditText texDiecc;
    Button contiFormu;
    TextView direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ubicacion_invitado);

        latitud=findViewById(R.id.txtLatitud_invitado);
        longitud=findViewById(R.id.txtLongitud_invitado);
        direccion=findViewById(R.id.txtDireccion_invitado);
        //irmapa=findViewById(R.id.buttonIrMapa);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }


//btnRegresarUbi=findViewById(R.id.btnRegresoUbica);
//tvMensaje=findViewById(R.id.tvMensaje);
        contiFormu = findViewById(R.id.butIrFormulario_invitado);

        contiFormu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle enviaDatos = new Bundle();
                enviaDatos.putString("KeyD", direccion.getText().toString());
                Intent intent = new Intent(MapsUbicacionInvitado.this, FormActivityDenuncia.class);
                intent.putExtras(enviaDatos);
                startActivity(intent);
            }
        });
        mRecycler = findViewById(R.id.recyclerViewSingle);
        texDiecc = findViewById(R.id.editTextDireccion);


        mRecycler = findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
//      Query query = mFirestore.collection("pet").whereEqualTo("id_user", mAuth.getCurrentUser().getUid());

       /* btnMuestraBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog = new BottomSheetDialog(MapsUbicacion.this);


                View vista = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_dialog, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);
                dialog.show();


            }


        });*/
       /* tvMensaje = findViewById(R.id.tvMensaje);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

        } else {
            iniciarLocalizacion();
        }*/

         BottomNavigationView navigationView = findViewById(R.id.bottom_navigation_incidencia_invitado);
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

    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMapsUbicacionInvitado(this);

        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }

        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,  0,(LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

        latitud.setText("Localizacion agregada");
        direccion.setText("");
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccion.setText(DirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        MapsUbicacionInvitado mapsUbicacion;

        public MapsUbicacionInvitado getMapsUbicacion() {
            return mapsUbicacion;
        }

        public void setMapsUbicacionInvitado(MapsUbicacionInvitado mapsUbicacion) {
            this.mapsUbicacion = mapsUbicacion;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();
            String sLatitud = String.valueOf(loc.getLatitude());
            String sLongitud = String.valueOf(loc.getLongitude());
            latitud.setText(sLatitud);
            longitud.setText(sLongitud);
            this.mapsUbicacion.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            latitud.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            latitud.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }


        public void mapa(double lat, double lon) {
            // Fragment del Mapa
            FragmentMaps fragment = new FragmentMaps();

            Bundle bundle = new Bundle();
            bundle.putDouble("lat", new Double(lat));
            bundle.putDouble("lon", new Double(lon));
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getMapsUbicacion().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment, fragment, null);

            fragmentTransaction.commit();


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
}