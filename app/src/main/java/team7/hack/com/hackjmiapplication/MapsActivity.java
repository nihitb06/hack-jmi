package team7.hack.com.hackjmiapplication;

import android.content.Context;
import android.Manifest;
import android.location.LocationManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    static Marker userMarker;

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10,20,locationListener);
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 20, locationListener);

                }

            }

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener =new LocationListener(){

            public void onLocationChanged(Location location){

                mMap.clear();
                LatLng UserLocation = new LatLng(location.getLatitude(), location.getLongitude());
                userMarker = mMap.addMarker(new MarkerOptions().position(UserLocation).title("Current Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                CameraPosition CamPos = new CameraPosition(UserLocation, 13,mMap.getCameraPosition().tilt,mMap.getCameraPosition().bearing);
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CamPos),4000, null);
                mMap.addMarker(new MarkerOptions().position(new LatLng(28.639495, 77.236723)).title("Blood Bank, LNPJ Hospital"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(28.633211, 77.308909)).title("Blood Donation, Max Hospital"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(28.572791, 77.245571)).title("BloodConnect"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(28.560687, 77.253609)).title("White Cross Blood bank"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(28.567319, 77.210140)).title("Blood Bank in AIIMS"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(28.513453, 77.242694)).title("Rotary Blood Bank"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(28.569948, 77.064390)).title("Artemis Blood Bank"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(28.700194, 77.174061)).title("Bloodcare.Club"));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }


            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(MapsActivity.this, "Enable GPS", Toast.LENGTH_SHORT).show();
            }
        };


        if (Build.VERSION.SDK_INT < 23) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 20, locationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 20, locationListener);


            }
        } else {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                //asking for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 20, locationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 20, locationListener);

            }

        }
    }
}
