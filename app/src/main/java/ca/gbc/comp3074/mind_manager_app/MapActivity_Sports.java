package ca.gbc.comp3074.mind_manager_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity_Sports extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_sports);

        TextView outdoorTitle = findViewById(R.id.outdoor_title);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            getLocation();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1 && permissions.length == 1
                && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION
                && grantResults [0] == PackageManager.PERMISSION_GRANTED){
            getLocation();
        }
    }

         void getLocation() {
             if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                     PackageManager.PERMISSION_GRANTED) {
                 LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                 if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                     Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                             100, 1, this);
                 } else {
                     Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                     startActivity(intent);
                 }
             }
         }

         @Override
         public void onLocationChanged(@NonNull Location location) {
             if(location != null){
                 LatLng pos = new LatLng (location.getLatitude(), location.getLongitude());
                 if(mMap != null){
                     mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
                 }
             }
         }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String title = extras.getString("sportTitle");
        TextView sportTitle;

        switch (title) {
            case "yoga":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Yogaspace");
                LatLng yoga = new LatLng(43.64756989927676, -79.42038278231642);
                mMap.addMarker(new MarkerOptions().position(yoga).title("Yogaspace"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yoga, 14));
                break;

            case "parks":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Bickford Park");
                LatLng parks = new LatLng(43.6611309882523, -79.41866381455726);
                mMap.addMarker(new MarkerOptions().position(parks).title("Bickford Park"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parks, 14));
                break;

            case "swimming pools":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Giovanni Caboto Outdoor Pool");
                LatLng swimming = new LatLng(43.67565803100579, -79.4516384908566);
                mMap.addMarker(new MarkerOptions().position(swimming).title("Giovanni Caboto Outdoor Pool"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(swimming, 14));
                break;

            case "running track":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Betty Sutherland Trail Park");
                LatLng running = new LatLng(43.765370661801995, -79.35150066892943);
                mMap.addMarker(new MarkerOptions().position(running).title("Betty Sutherland Trail Park"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(running, 14));
                break;

            case "paintball":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Defcon Paintball Toronto");
                LatLng paintball = new LatLng(43.805210990931315, -79.33769468365689);
                mMap.addMarker(new MarkerOptions().position(paintball).title("Defcon Paintball Toronto"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paintball, 14));
                break;

            case "biking trail":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Don Mills Trail");
                LatLng biking = new LatLng(43.7344340084413, -79.3543440865438);
                mMap.addMarker(new MarkerOptions().position(biking).title("Don Mills Trail"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(biking, 14));
                break;

            case "boxing":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Hardknocks Boxing Club");
                LatLng boxing = new LatLng(43.65581723204526, -79.36400749380303);
                mMap.addMarker(new MarkerOptions().position(boxing).title("Hardknocks Boxing Club"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(boxing, 14));
                break;

            case "golf course":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Rosedale Golf Club");
                LatLng golf = new LatLng(43.73648412211719, -79.39925596141822);
                mMap.addMarker(new MarkerOptions().position(golf).title("Rosedale Golf Club"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(golf, 14));
                break;

            case "hiking area":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("William Baker Trail - Hiking Area");
                LatLng hiking = new LatLng(43.743877241747384, -79.48423049412723);
                mMap.addMarker(new MarkerOptions().position(hiking).title("William Baker Trail - Hiking Area"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hiking, 14));
                break;

            case "laser tag":
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Electric Perfume");
                LatLng laser = new LatLng(43.67949251062376, -79.34139314299918);
                mMap.addMarker(new MarkerOptions().position(laser).title("Electric Perfume"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(laser, 14));
                break;

            default:
                sportTitle = findViewById(R.id.sports_title);
                sportTitle.setText("Casa loma");
                LatLng casaloma = new LatLng(43.6705, -79.3936);
                mMap.addMarker(new MarkerOptions().position(casaloma).title("Casa Loma"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(casaloma, 14));
                break;
        }
    }

    private void backToWelcome(){
        Intent start = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(start);
    }

    //function to start AboutActivity
    private void openAbout(){
        Intent start = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(start);
    }

    private void openlogin(){
        Intent start = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            openAbout();
            return true;
        }
        if (item.getItemId() == R.id.menu_main) {
            openlogin();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}