package justparking.justparking;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class Parking1 extends AppCompatActivity {

    private EditText NumberOfParking;
    private Button Opendialog;
    public static LatLng just, crunetLocation;
    private LocationManager locationManager;
    private LocationListener listener;
    private String number;
    private String DurationTime;
    private TextView t[];


    int id[] = {
            R.id.p1,R.id.p2,R.id.p3,R.id.p4,R.id.p5,R.id.p6,R.id.p7,R.id.p8,R.id.p9,R.id.p10,R.id.p11,R.id.p12,R.id.p13,R.id.p14,R.id.p15,
            R.id.p16,R.id.p17,R.id.p18,R.id.p19,R.id.p20,R.id.p21,R.id.p22,R.id.p23,R.id.p24,R.id.p25,R.id.p26,R.id.p27,R.id.p28,R.id.p29,R.id.p30,
            R.id.p31,R.id.p32,R.id.p33,R.id.p34,R.id.p35,R.id.p36,R.id.p37,R.id.p38,R.id.p39,R.id.p40,R.id.p41,R.id.p42,R.id.p43,R.id.p44,R.id.p45,
            R.id.p46,R.id.p47,R.id.p48,R.id.p49,R.id.p50,R.id.p51,R.id.p52,R.id.p54,R.id.p55,R.id.p56,R.id.p57,R.id.p58,R.id.p59,R.id.p60,R.id.p61,
            R.id.p62,R.id.p63,R.id.p64,R.id.p65,R.id.p67,R.id.p68,R.id.p69,R.id.p70,R.id.p71,R.id.p72,R.id.p73,R.id.p74,R.id.p75,R.id.p76,R.id.p77,
            R.id.p78,R.id.p79,R.id.p80,R.id.p81,R.id.p82,R.id.p83,R.id.p84,R.id.p85,R.id.p86,R.id.p87,R.id.p88,R.id.p90,R.id.p91,R.id.p92,R.id.p93,
            R.id.p94,R.id.p95,R.id.p96,R.id.p97,R.id.p98,R.id.p99,R.id.p100,R.id.p101,R.id.p102,R.id.p103,R.id.p104,R.id.p105,R.id.p106,R.id.p107,R.id.p108,
            R.id.p109,R.id.p110,R.id.p111,R.id.p112,R.id.p113,R.id.p114,R.id.p115,R.id.p116,R.id.p117,R.id.p118,R.id.p119,R.id.p120,R.id.p121,R.id.p122,R.id.p123,
            R.id.p124,R.id.p125,R.id.p126,R.id.p127,R.id.p128,R.id.p129,R.id.p130,R.id.p131,R.id.p132,R.id.p133,R.id.p134,R.id.p135,R.id.p136,R.id.p137,R.id.p138,
            R.id.p139,R.id.p140,R.id.p141,R.id.p142,R.id.p143,R.id.p144,R.id.p145,R.id.p146,R.id.p147,R.id.p148,R.id.p149,R.id.p150,R.id.p151,R.id.p152,R.id.p153,
            R.id.p154,R.id.p155,R.id.p156,R.id.p157,R.id.p158,R.id.p159,R.id.p160,R.id.p161,R.id.p162,R.id.p163,R.id.p164,R.id.p165,R.id.p166,R.id.p167,R.id.p168,
            R.id.p169,R.id.p170,R.id.p171,R.id.p172,R.id.p173,R.id.p174,R.id.p175,R.id.p176,R.id.p177,R.id.p178,R.id.p179,R.id.p180,R.id.p181,R.id.p182,R.id.p183,
            R.id.p184,R.id.p185,R.id.p186,R.id.p187,R.id.p188,R.id.p189,R.id.p190,R.id.p191,R.id.p192,R.id.p193,R.id.p194,R.id.p195,R.id.p196,R.id.p197,R.id.p198,
            R.id.p199,R.id.p200,R.id.p201,R.id.p202,R.id.p203,R.id.p204,R.id.p205,R.id.p206,R.id.p207,R.id.p208,R.id.p209,R.id.p210,R.id.p211,R.id.p212,R.id.p213,
            R.id.p214,R.id.p215,R.id.p216,R.id.p217,R.id.p218,R.id.p219,R.id.p220,R.id.p221,R.id.p222
    };
    private ArrayList<ParkingLocation> p1=new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking1);

        NumberOfParking = findViewById(R.id.NumberOfRegster);

        t = new TextView[219];

        for (int i = 0; i < 219; i++) {

            t[i] = (TextView) findViewById(id[i]);
            t[i].setBackgroundColor(Color.GREEN);
        }


        NumberOfParking=findViewById(R.id.NumberOfRegster);
        Opendialog=findViewById(R.id.ok);
        Opendialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=NumberOfParking.getText().toString();
                if(number.equals(""))
                {
                    Toast.makeText(Parking1.this, "please Select a number of parking", Toast.LENGTH_SHORT).show();
                }
                else {
                    int n=0;
                    if(Integer.parseInt(number)>1) {
                        n =Integer.parseInt(number)-1;
                    }

                    if(FindIdOfParking(""+n))
                    {
                        Intent intent=new Intent(Parking1.this,CreateBookings.class);
                        //intent.putExtra("NumberOfParking",""+n);
                        MainActivity.Number=""+(n+1);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                        finish();
                    }
                    else if(n>t.length)
                    {
                        Toast.makeText(Parking1.this, "The number of parking is not found", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Parking1.this, "The number of parking "+(n+1)+" is booked up", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Parkings").child("Mbabane");
        readFrameDataBase();


    }

    private void SetColor() {
        for(int i=0;i<p1.size();i++) {
            if (p1.get(i).isStatus())
                t[Integer.parseInt(p1.get(i).getId())].setBackgroundColor(Color.GREEN);
            else
                t[i].setBackgroundColor(Color.RED);
        }
    }



    private boolean FindIdOfParking(String number) {
        for(int i=0;i<p1.size();i++)
        {

            if (p1.get(i).getId().equals(number)) {
                if(p1.get(i).isStatus()) {
                    just = new LatLng(p1.get(i).getLat(), p1.get(i).getLon());
                    MyParking.parking.setId(p1.get(i).getId());
                    MyParking.parking.setLat(""+p1.get(i).getLat());
                    MyParking.parking.setLon(""+p1.get(i).getLon());
                    MyParking.parking.setRand(p1.get(i).getRand());
                    return true;
                }
            }

        }
        return false;
    }

    private void readFrameDataBase() {


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    ParkingLocation parkingLocation = new ParkingLocation();
                    String id = (String) dataSnapshot1.child("id").getValue().toString();
                    double lat = Double.parseDouble(dataSnapshot1.child("lat").getValue().toString());
                    double lon = Double.parseDouble(dataSnapshot1.child("lon").getValue().toString());
                    boolean stutus = Boolean.parseBoolean(dataSnapshot1.child("status").getValue().toString());
                    String rand = (String) dataSnapshot1.child("rand").getValue();
                    parkingLocation.setId(id);
                    parkingLocation.setLat(lat);
                    parkingLocation.setLon(lon);
                    parkingLocation.setStatus(stutus);
                    parkingLocation.setRand(rand);
                    p1.add(parkingLocation);

                }

                for(int i=0;i<p1.size();i++) {
                    if (!p1.get(i).isStatus())
                        t[Integer.parseInt(p1.get(i).getId())].setBackgroundColor(Color.RED);

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
        // SetColor();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  readFrameDataBase();
        // SetColor();
    }
}
