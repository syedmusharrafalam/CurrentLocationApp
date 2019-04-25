package com.example.currentlocationapp;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class LatLngActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lat_lng);


        requestPermission();
        getAndSendLatLng();


    }


    public void getAndSendLatLng(){
        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(LatLngActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        client.getLastLocation().addOnSuccessListener(LatLngActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null)
                {
                    String lat = String.valueOf(location.getLatitude());
                    String lng = String.valueOf(location.getLongitude());
                    TextView textView = findViewById(R.id.txtViewLat);
                    textView.setText(lat);
                    TextView textView1 = findViewById(R.id.txtViewLng);
                    textView1.setText(lng);
                    RequestQueue queue = Volley.newRequestQueue(LatLngActivity.this);

                    String url = "https://www.google.com/maps/dir/?api=1&destination=" + location.getLongitude()+ "," + location.getLatitude()+ "&travelmode=driving";
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    Log.d("response",response);

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //textView.setText("That didn't work!");
                        }
                    });

// Add the request to the RequestQueue.
                    queue.add(stringRequest);


                    //                    String strAdd = "";
//                    Geocoder geocoder = new Geocoder(LatLngActivity.this, Locale.getDefault());
//                    try {
//                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                        if (addresses != null) {
//                            Address returnedAddress = addresses.get(0);
//                            StringBuilder strReturnedAddress = new StringBuilder("");
//
//                            for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
//                                strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
//                            }
//                            strAdd = strReturnedAddress.toString();
//                            Toast.makeText(getApplicationContext(),strAdd,Toast.LENGTH_LONG).show();
//                            Log.w("Current loction address", strReturnedAddress.toString());
//                        } else {
//                            Log.w("Current loction address", "No Address returned!");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Log.w("loction address", "Canont get Address!");
//                    }
                }

            }
        });





    }


    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }

}
