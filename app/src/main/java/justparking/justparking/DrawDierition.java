package justparking.justparking;

import android.*;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawDierition extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_REQUEST=500;
    ArrayList<LatLng> listPoints;

    LatLng latLng3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_dierition);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listPoints=new ArrayList<>();
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
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST );
            return;
        }
        mMap.setMyLocationEnabled(true);
        // mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng just = new LatLng(32.497410,35.989815);

        mMap.addMarker(new MarkerOptions().position(Parking1.just).title("Marker parking in  just"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Parking1.just));

        LatLng coordinate =  new LatLng(32.54292569357152,35.890879606249754);

        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(Parking1.crunetLocation, 15);
        mMap.animateCamera(yourLocation);


        DrawDaerction();
    }

    public  void DrawDaerction()
    {
        //Toast.makeText(this, ""+Parking1.just.toString(), Toast.LENGTH_SHORT).show();
        String url =getRequestURL(Parking1.just,Parking1.crunetLocation);
        TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
        taskRequestDirections.execute(url);
    }

    private String getRequestURL(LatLng origin, LatLng dest) {
        //value of origin
        String str_org="origin="+origin.latitude+","+origin.longitude;
        //value of destination
        String str_dest="destination="+dest.latitude+","+dest.longitude;
        //Set value enable the sensor
        String sensor="sensor=false";
        //Mode for find direction
        String mode ="mode=driving";
        //build the full param
        String param=str_org+"&"+str_dest+"&"+sensor+"&"+mode;
        //Output format
        String outout="json";
        //create url to request
        String url="https://maps.googleapis.com/maps/api/directions/"+outout+"?"+param;
        return url;
    }

    private String requestDirection(String req_Url) throws IOException {
        String responseString="";
        InputStream inputStream=null;
        HttpURLConnection httpURLConnection=null;
        try {
            URL url=new URL(req_Url);
            httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            //Get the response result
            inputStream=httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer=new StringBuffer();
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            responseString=stringBuffer.toString();
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream!=null)
            {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case LOCATION_REQUEST:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }
    }
    public class TaskRequestDirections extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String responseString="";
            try {
                responseString=requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>> >{


        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            //Get list route and display it into the map

            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions!=null) {
                mMap.addPolyline(polylineOptions);
            } else {

                Toast.makeText(getApplicationContext(), "Direction not found!", Toast.LENGTH_SHORT).show();
                DrawDaerction();
            }
        }
    }
}

