package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    GoogleMap mapa;

    int contador;
    ArrayList<LatLng> puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        contador = 0;
        puntos = new ArrayList<LatLng>();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);

       // LatLng madrid = new LatLng(-1.0122163925312702, -79.46962177336476);
      //  CameraPosition camPos = new CameraPosition.Builder()
        //        .target(madrid)
          //      .zoom(25)
            //    .bearing(45) //noreste arriba
              //  .tilt(70) //punto de vista de la cámara 70 grados
                //.build();
      //  CameraUpdate camUpd3 =
        //        CameraUpdateFactory.newCameraPosition(camPos);
        //mapa.animateCamera(camUpd3);

        CameraUpdate camUpd1 = CameraUpdateFactory
                .newLatLngZoom(new LatLng(-1.0122, -79.4696), 20);
        mapa.moveCamera(camUpd1);
        mapa.setOnMapClickListener(this);

    }

    @Override
    public void onMapClick(LatLng latLng) {

        /*Projection proj = mapa.getProjection();
        Point coord = proj.toScreenLocation(latLng);*/

        TextView lblLat = findViewById(R.id.lblLatitud);
        lblLat.setText(String.format("%.4f",latLng.latitude));

        TextView lblLong = findViewById(R.id.lblLongotid);
        lblLong.setText(String.format("%.4f",latLng.longitude));

        mapa.addMarker(new MarkerOptions().position(latLng)
                .title("Marcador"));

        contador = contador + 1;
        puntos.add(latLng);
        if(contador == 4){
            PolylineOptions lineas = new PolylineOptions()
                    .add(puntos.get(0))
                    .add(puntos.get(1))
                    .add(puntos.get(2))
                    .add(puntos.get(3))
                    .add(puntos.get(0));
            lineas.width(8);
            lineas.color(Color.RED);
            mapa.addPolyline(lineas);
            contador = 0;
            puntos.clear();
        }

        /*
        TextView lblLat = findViewById(R.id.lblLatitud);
        TextView lblLong = findViewById(R.id.lblLongotid);

        // Formatear latitud y longitud a 4 decimales
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        String formattedLat = decimalFormat.format(latLng.latitude);
        String formattedLong = decimalFormat.format(latLng.longitude);

        lblLat.setText(formattedLat);
        lblLong.setText(formattedLong);*/


        /*Toast.makeText(
                MainActivity.this,
                "Click\n" +
                        "Lat: " + latLng.latitude + "\n" +
                        "Lng: " + latLng.longitude + "\n" +
                        "X: " + coord.x + " - Y: " + coord.y,
                Toast.LENGTH_SHORT).show();*/
    }
    public void PintarRectUteq(View view){
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0134, -79.4672)));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0123, -79.4671)));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0120, -79.4714)));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0131, -79.4716)));

        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(-1.0134, -79.4672))
                .add(new LatLng(-1.0123, -79.4671))
                .add(new LatLng(-1.0120, -79.4714))
                .add(new LatLng(-1.0131, -79.4716))
                .add(new LatLng(-1.0134, -79.4672));
        lineas.width(8);
        lineas.color(Color.RED);
        mapa.addPolyline(lineas);
    }
}