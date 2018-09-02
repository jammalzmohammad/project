package justparking.justparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Class_buildings extends AppCompatActivity {

    Button GotoParking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_buildings);
        GotoParking=(Button)findViewById(R.id.gotopaeking);
        GotoParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parking1=new Intent(Class_buildings.this,Parking1.class);
                startActivity(parking1);
                finish();
            }
        });
    }
}
