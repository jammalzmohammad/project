package justparking.justparking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivty extends AppCompatActivity {

    private EditText email,password;
    private AppCompatButton Singup;
    private Button login;
    private FirebaseAuth mAuth;
    private ProgressDialog myProgressBarLogin;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        Singup=(AppCompatButton)findViewById(R.id.singup);
        email=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText3);
        login=(Button) findViewById(R.id.login);
       // toolbar = (Toolbar) findViewById(R.id.LoginToolbar);
      //  setSupportActionBar(toolbar);
   //     getSupportActionBar().setTitle("Login ");
        myProgressBarLogin=new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().isEmpty()||!password.getText().toString().isEmpty()) {
                    // Singin(email.getText().toString(), password.getText().toString());
                    myProgressBarLogin.setTitle("Logging In");
                    myProgressBarLogin.setMessage("please wait we while check your credentials");
                    myProgressBarLogin.setCanceledOnTouchOutside(false);
                    Singin(email.getText().toString(), password.getText().toString());
                }else
                    Toast.makeText(StartActivty.this,"please try again some think Wrong ",Toast.LENGTH_LONG).show();

            }
        });

        Singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CrateIntent=new Intent(StartActivty.this,CreatAcount.class);
                startActivity(CrateIntent);
            }
        });
    }

    private void Singin(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            myProgressBarLogin.dismiss();
                            Intent LoginIntent=new Intent(StartActivty.this,MainActivity.class);
                            startActivity(LoginIntent);
                            finish();
                        } else {
                            myProgressBarLogin.hide();
                            Toast.makeText(StartActivty.this,"Cannot Sing in. Please check from and try again",Toast.LENGTH_LONG).show();

                        }

                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StartActivty.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
