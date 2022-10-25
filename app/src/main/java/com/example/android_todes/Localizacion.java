package com.example.android_todes;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class Localizacion implements LocationListener {

    MapsUbicacion mapsUbicacion;
    TextView tvMensaje;

    public MapsUbicacion getMapsUbicacion(){
        return mapsUbicacion;
    }

    public void setMapsUbicacion(MapsUbicacion mapsUbicacion, TextView tvMensaje) {
        this.mapsUbicacion = mapsUbicacion;
        this.tvMensaje = tvMensaje;
    }

    @Override
    public void onLocationChanged(Location location) {
        // Este metodo se ejecuta cuando el GPS recibe nuevas coordenadas
        String texto = "Mi ubicación es: \n"
                + "Latitud = " + location.getLatitude() + "\n"
                + "Longitud = " + location.getLongitude();

        tvMensaje.setText(texto);

        mapa(location.getLatitude(), location.getLongitude());
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



    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}
