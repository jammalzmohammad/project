package justparking.justparking;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyBooking extends AppCompatActivity {

    private TextView Name ,Time;
    private Button cancel,GoToMap;
    String Number;
    private LocationManager locationManager;
    private LocationListener listener;
    public static boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        Name=(TextView) findViewById(R.id.bookingsName_id);
        Time=(TextView)findViewById(R.id.time_Bookings_id);
        cancel=(Button)findViewById(R.id.cancel_booking_id);
        GoToMap=(Button)findViewById(R.id.gotomap_booking_id);
        Intent intent=getIntent();
        //Number=CreateBookings.Number;
        Number=MainActivity.Number;
        // Toast.makeText(this, "on My booking"+Number, Toast.LENGTH_SHORT).show();

        GoToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyBooking.this,DrawDierition.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBooking();

                Intent intent1=new Intent(MyBooking.this,MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                MyParking.parking.SetParkingTrue();
                startActivity(intent1);
                finish();

            }
        });


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener =new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Parking1.crunetLocation = new LatLng(location.getLatitude(), location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates("gps", 100, 0, listener);
    }

    private void cancelBooking() {
        FirebaseUser User= FirebaseAuth.getInstance().getCurrentUser();
        String UID=User.getUid();
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference("Bookings").child(UID);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    if(Boolean.parseBoolean(dataSnapshot1.child("status").getValue().toString()))
                    {
                        String id= (String) dataSnapshot1.child("id").getValue();
                        FirebaseUser User= FirebaseAuth.getInstance().getCurrentUser();
                        String UID=User.getUid();
                        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Bookings").child(UID).child(id);
                        myRef.child("status").setValue("false");
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void ReadBookingAvailable() {

        FirebaseUser User= FirebaseAuth.getInstance().getCurrentUser();
        String UID=User.getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Bookings").child(UID);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    if(Boolean.parseBoolean(dataSnapshot1.child("status").getValue().toString())){
                        String id= (String) dataSnapshot1.child("id").getValue();
                        Name.setText(dataSnapshot1.child("loca").getValue().toString());
                        Time.setText(dataSnapshot1.child("time").getValue().toString()+" Hour");
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ReadBookingAvailable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Name.setText(BookingParking.bookingParking.getLoca());
        Time.setText(BookingParking.bookingParking.getTime()+" Hour");
        ReadBookingAvailable();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ReadBookingAvailable();

    }
}
