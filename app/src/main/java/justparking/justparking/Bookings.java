package justparking.justparking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Bookings extends AppCompatActivity {

    private ListView listView ;
    private DatabaseReference database ;
    public ArrayList<BookingParking> bookingParkingArrayList ;
    private ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        bookingParkingArrayList=new ArrayList<>();
        progressBar = findViewById(R.id.mainProgressBar);
        listView = findViewById(R.id.bookingsListView);
        FirebaseUser User= FirebaseAuth.getInstance().getCurrentUser();
        String UID=User.getUid();
        database = FirebaseDatabase.getInstance().getReference("Bookings").child(UID);
        progressBar.setVisibility(View.VISIBLE);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    BookingParking bookingParking = new BookingParking();
                    bookingParking.setLoca(dataSnapshot1.child("loca").getValue().toString());
                    bookingParking.setTime(dataSnapshot1.child("time").getValue().toString());
                    bookingParking.setStatus(Boolean.parseBoolean(dataSnapshot1.child("status").getValue().toString()));
                    bookingParkingArrayList.add(bookingParking);
                }
                ArrayAdapter<BookingParking> bookingParkingArrayAdapter = new BookingsAdapter(getApplicationContext(), bookingParkingArrayList);
                listView.setAdapter(bookingParkingArrayAdapter);
                progressBar.setVisibility(View.INVISIBLE);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Bookings.this, "some error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


    }


}
