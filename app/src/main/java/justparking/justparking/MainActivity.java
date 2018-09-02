package justparking.justparking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //implements NavigationView.OnNavigationItemSelectedListener
    private Parking p=new Parking();
    private ArrayList<ParkingLocation> parkingLocation1 = new ArrayList<>();

    private TextView t1, t2, Textcounter,bookingname,bookingtime;
    private Button SaveInList, GetGps,SaveInDataBase,canselBooking;
    private LocationManager locationManager;
    private LocationListener listener;
    private int count=2;
    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private UserInfo userInfo=new UserInfo();
    private TextView name_drawer,email_drawer;
    private Button buildings, engineering,medical;
    private ImageView ImageParking;
    Parking p1=new Parking();
    FirebaseDatabase database;
    DatabaseReference myRef;
    double lan=0,lon=0;
    CardView cardView;
    ArrayList<BookingParking> bookingParkingArrayList;
    ParkingLocation parkingLocation;
    public static String Number;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        setContentView(R.layout.activity_get_parking);

        //TextView
        t1 = (TextView) findViewById(R.id.TextLang_id);
        t2 = (TextView) findViewById(R.id.TextLat_id2);
        Textcounter = (TextView) findViewById(R.id.NumberOFParking_id);

        //button
        SaveInList = (Button) findViewById(R.id.SaveInList);
        GetGps = (Button) findViewById(R.id.GetGps);
        SaveInDataBase=(Button)findViewById(R.id.SaveInDataBase);
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("Parkings").child("Mbabane");

        SaveInList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // parkingLocation1.add(parkingLocation);
                Textcounter.setText(""+count);
                Toast.makeText(MainActivity.this, "The Parking id is "+count+" Parking save", Toast.LENGTH_SHORT).show();
                count++;

            }
        });

        SaveInDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Textcounter.setText(""+count);
                parkingLocation = new ParkingLocation(lan,lon,false,""+count);
                DatabaseReference newRef = myRef.push();
                newRef.setValue(parkingLocation);
                Toast.makeText(MainActivity.this, "is save", Toast.LENGTH_SHORT).show();
            }
        });


        //Location service
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //location Listener
        listener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                lan=location.getLatitude();
                lon=location.getLongitude();
                t1.setText(""+location.getLatitude());
                t2.setText(""+location.getLongitude());
                Toast.makeText(MainActivity.this, "The Gps is cahnge", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);

            }
        };



        //
        configure_button();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        GetGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates("gps", 1000, 0, listener);
            }
        });
    }
}
*/

        try {
            setContentView(R.layout.drawrlayout);
            mAuth = FirebaseAuth.getInstance();

            toolbar = (Toolbar) findViewById(R.id.mainToolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("JUST e-Parking");

            NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
            View headerView = navigationView.getHeaderView(0);
            name_drawer = (TextView) headerView.findViewById(R.id.Name_Drawer);
            email_drawer = (TextView) headerView.findViewById(R.id.Emil_Drawer);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            navigationView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
            drawerLayout.setDrawerListener(toggle);
            toggle.syncState();

            //button
            buildings = (Button) findViewById(R.id.buildings);
            engineering=(Button)findViewById(R.id.engineering);
            medical=(Button)findViewById(R.id.medical);

            buildings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToClassBuildings=new Intent(MainActivity.this,Class_buildings.class);
                    startActivity(goToClassBuildings);
                }
            });

            engineering=(Button)findViewById(R.id.engineering);
            engineering.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent GoToEngineering=new Intent(MainActivity.this,Engineering.class);
                    startActivity(GoToEngineering);
                }
            });

            medical=(Button)findViewById(R.id.medical);
            medical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent GoToMedical=new Intent(MainActivity.this,Medical.class);
                    startActivity(GoToMedical);
                }
            });

            bookingParkingArrayList=new ArrayList<>();
            ReadBooking();

        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void ReadBooking() {

        FirebaseUser User= FirebaseAuth.getInstance().getCurrentUser();
        String UID=User.getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Bookings").child(UID);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart () {
        super.onStart();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent StartIntent = new Intent(MainActivity.this, StartActivty.class);
            startActivity(StartIntent);
            finish();
        }
        else
        {
            BookingAvailable();
        }


    }

    private void BookingAvailable() {

        FirebaseUser User= FirebaseAuth.getInstance().getCurrentUser();
        String UID=User.getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Bookings").child(UID);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    if(Boolean.parseBoolean(dataSnapshot1.child("status").getValue().toString()))
                    {
                        Intent MyBooking=new Intent(MainActivity.this, MyBooking.class);
                        startActivity(MyBooking);
                        finish();
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
    protected void onResume() {
        super.onResume();
        ReadDataFromServer();
    }

    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item){
        int id = item.getItemId();
        switch (id) {
            case R.id.bookings:
                Intent gotoBookings=new Intent(MainActivity.this,Bookings.class);
                startActivity(gotoBookings);
                break;

            case R.id.logout1:
                FirebaseAuth.getInstance().signOut();
                userInfo.setName("");
                userInfo.setEmail("");
                Intent intent = new Intent(MainActivity.this, StartActivty.class);
                startActivity(intent);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ReadDataFromServer()
    {
        FirebaseUser User=FirebaseAuth.getInstance().getCurrentUser();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String UID = User.getUid();
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("User").child(UID);


            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    userInfo.setName(dataSnapshot.child("name").getValue().toString());
                    userInfo.setEmail(dataSnapshot.child("email").getValue().toString());
                    name_drawer.setText(userInfo.getName());
                    email_drawer.setText(userInfo.getEmail());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else
        {
            Toast.makeText(this, "you get  Error", Toast.LENGTH_SHORT).show();
        }
    }
}

