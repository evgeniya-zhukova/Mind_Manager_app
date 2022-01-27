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

public class MapActivity_Outdoors extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_outdoors);

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
        String title = extras.getString("outdoorTitle");
        TextView outdoorTitle;

        switch (title) {
            case "fishing":
                outdoorTitle = findViewById(R.id.outdoor_title);
                outdoorTitle.setText("Grenadier Pond");
                LatLng fishing = new LatLng(43.64106388801521, -79.46687074751708);
                mMap.addMarker(new MarkerOptions().position(fishing).title("Grenadier Pond"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fishing, 14));
                break;

            case "parks":
                outdoorTitle = findViewById(R.id.outdoor_title);
                outdoorTitle.setText("High Park");
                LatLng parks = new LatLng(43.64655677959097, -79.46369134087968);
                mMap.addMarker(new MarkerOptions().position(parks).title("High Park"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parks, 14));
                break;

            case "hangout":
                outdoorTitle = findViewById(R.id.outdoor_title);
                outdoorTitle.setText("Ripley's Aquarium of Canada");
                LatLng hangout = new LatLng(43.64241837384692, -79.3859736076201);
                mMap.addMarker(new MarkerOptions().position(hangout).title("Ripley's Aquarium of Canada"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hangout, 14));
                break;

            case "running track":
                outdoorTitle = findViewById(R.id.outdoor_title);
                outdoorTitle.setText("Roycroft Park Lands");
                LatLng running = new LatLng(43.68058119852502, -79.40405219491038);
                mMap.addMarker(new MarkerOptions().position(running).title("Roycroft Park Lands"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(running, 14));
                break;

            case "paintball":
                outdoorTitle = findViewById(R.id.outdoor_title);
                outdoorTitle.setText("Sgt Splatters Paintball");
                LatLng paintball = new LatLng(43.70365967562423, -79.45592088127125);
                mMap.addMarker(new MarkerOptions().position(paintball).title("Sgt Splatters Paintball"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paintball, 14));
                break;

            case "biking trail":
                outdoorTitle = findViewById(R.id.outdoor_title);
                outdoorTitle.setText("Sunnyside Bike Park");
                LatLng biking = new LatLng(43.63681690164043, -79.46531656287296);
                mMap.addMarker(new MarkerOptions().position(biking).title("Sunnyside Bike Park"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(biking, 14));
                break;

            case "shopping":
                outdoorTitle = findViewById(R.id.outdoor_title);
                outdoorTitle.setText("Dufferin Mall");
                LatLng shopping = new LatLng(43.655838139429626, -79.43569939132914);
                mMap.addMarker(new MarkerOptions().position(shopping).title("Dufferin Mall"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shopping, 14));
                break;

            case "massage therapy":
                outdoorTitle = findViewById(R.id.outdoor_title);
                outdoorTitle.setText("Ted Thomas Registered Massage Therapist");
                LatLng massage = new LatLng(43.66524825495547, -79.41203234539495);
                mMap.addMarker(new MarkerOptions().position(massage).title("Ted Thomas Registered Massage Therapist"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(massage, 14));
                break;

            default:
                outdoorTitle = findViewById(R.id.outdoor_title);
                outdoorTitle.setText("Casa Loma");
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