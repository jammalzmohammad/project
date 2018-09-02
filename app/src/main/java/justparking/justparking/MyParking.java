package justparking.justparking;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyParking {

    private String id;
    private String lat;
    private String lon;
    private String rand;
    private String status;
    public static  MyParking parking=new MyParking();

    public MyParking(String id, String lat, String lon, String rand, String status) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.rand = rand;
        this.status = status;
    }

    public MyParking( ) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getRand() {
        return rand;
    }

    public void setRand(String rand) {
        this.rand = rand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void SetParkingFalse()
    {
        DatabaseReference myR = FirebaseDatabase.getInstance().getReference("Parkings").child("Mbabane").child(rand);
        myR.child("status").setValue("false").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               // Toast.makeText(CreateBookings.this, "is false", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SetParkingTrue()
    {
        DatabaseReference myR = FirebaseDatabase.getInstance().getReference("Parkings").child("Mbabane").child(rand);
        myR.child("status").setValue("true").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               // Toast.makeText(CreateBookings.this, "is false", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
