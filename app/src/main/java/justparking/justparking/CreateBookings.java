package justparking.justparking;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CreateBookings extends AppCompatActivity {

    private Button Create,cansel;
    private EditText DurationTime;
    static String Number;
    Intent MyBooking;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bookings);

        Create=(Button)findViewById(R.id.gotomap);
        cansel=(Button)findViewById(R.id.cancel);

        DurationTime=(EditText)findViewById(R.id.DurationTime);

        cansel=(Button)findViewById(R.id.cancel);
        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CreateBookings.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String time=DurationTime.getText().toString();
                FirebaseUser User= FirebaseAuth.getInstance().getCurrentUser();
                String UID=User.getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference().child("Bookings").child(UID);

                //  Intent intent=getIntent();
                Number= MainActivity.Number;
                //MainActivity.Number=Number;
                //  Toast.makeText(CreateBookings.this, "on create"+Number, Toast.LENGTH_SHORT).show();

                DatabaseReference newRef = myRef.push();
                BookingParking Boook=new BookingParking(""+time,"class buildings "+Number,true,newRef.getKey());

                BookingParking.bookingParking.setTime(time);
                BookingParking.bookingParking.setLoca("class buildings "+Number);

                newRef.setValue(Boook);
                MyBooking=new Intent(CreateBookings.this,MyBooking.class);

                //Notification
                Calendar crentTime=Calendar.getInstance();

                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,crentTime.get(Calendar.HOUR_OF_DAY)+Integer.parseInt(time));
                calendar.set(Calendar.MINUTE,crentTime.get(Calendar.MINUTE));
                calendar.set(Calendar.SECOND,Calendar.SECOND);
                Intent notification=new Intent(getApplicationContext(),Notification_receiver.class);

                PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),100,
                        notification,PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

                MyBooking.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);


                MyParking.parking.SetParkingFalse();
                startActivity(MyBooking);
                finish();



            }
        });

    }

}
