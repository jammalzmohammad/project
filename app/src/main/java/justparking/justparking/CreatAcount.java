package justparking.justparking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatAcount extends AppCompatActivity {

    private AppCompatEditText user;
    private AppCompatEditText pass;
    private AppCompatEditText conf_pass;
    private AppCompatEditText email;
    private RelativeLayout relativeLayout;
    private TextInputLayout userLayout,emailLayout,passwordLayout,ConfermPasswordLayout;
    private Button createAcount;
    private String password;
    private FirebaseAuth mAuth;
    private ProgressDialog mLogin;
    private Toolbar toolbar;
    private FirebaseDatabase database;
    private  DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_acount);

        user = (AppCompatEditText) findViewById(R.id.username_TextField);
        userLayout = (TextInputLayout) findViewById(R.id.username_TextInputLayout);
        pass = (AppCompatEditText) findViewById(R.id.password_TextField);
        ConfermPasswordLayout=(TextInputLayout)findViewById(R.id.Conferm_password_TextInputLayout);
        conf_pass = (AppCompatEditText) findViewById(R.id.conf_password_TextField);
        passwordLayout=(TextInputLayout)findViewById(R.id.password_TextInputLayout);
        email=(AppCompatEditText)findViewById(R.id.email_TextField);
        emailLayout=(TextInputLayout)findViewById(R.id.email_TextInputLayout);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        createAcount=(Button)findViewById(R.id.create);
        relativeLayout.setOnClickListener(null);
        mAuth = FirebaseAuth.getInstance();
        mLogin=new ProgressDialog(this);
        //toolbar
        toolbar = (Toolbar) findViewById(R.id.CreateToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Acount ");


        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (user.getText().toString().isEmpty()){

                    userLayout.setErrorEnabled(true);
                    userLayout.setError("please enter your username!");

                }else {

                    userLayout.setErrorEnabled(false);
                }
            }
        });

        user.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (user.getText().toString().isEmpty()){

                    userLayout.setErrorEnabled(true);
                    userLayout.setError("please enter your username!");

                }else {

                    userLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password=pass.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String regex ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!^&*)(@#$%]).{6,15})";
                Pattern patt = Pattern.compile(regex);
                Matcher matcher = patt.matcher(password);
                if(!matcher.matches())
                {
                    passwordLayout.setErrorEnabled(true);
                    passwordLayout.setError("your password is weak");
                }
                else
                {
                    passwordLayout.setErrorEnabled(false);
                }
            }
        });

        conf_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password=pass.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //cpassword=

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!conf_pass.getText().toString().equals(pass.getText().toString()))
                {
                    ConfermPasswordLayout.setErrorEnabled(true);
                    ConfermPasswordLayout.setError("Your password is not mather");
                }
                else
                {
                    ConfermPasswordLayout.setErrorEnabled(false);
                }

            }
        });


        createAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!email.getText().toString().isEmpty()||!pass.getText().toString().isEmpty())
                {
                    mLogin.setTitle("Registering user");
                    mLogin.setMessage("please wait while we create your account");
                    mLogin.setCanceledOnTouchOutside(false);
                    mLogin.show();
                    createAcoun(email.getText().toString(),pass.getText().toString(),user.getText().toString());
                }
            }
        });

    }

    private void createAcoun(final String email, String password, final String Name) {


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser User=FirebaseAuth.getInstance().getCurrentUser();
                            String UID=User.getUid();
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference().child("User").child(UID);

                            UserInfo userInfo=new UserInfo();
                            userInfo.setName(Name);
                            userInfo.setEmail(email);
                            myRef.setValue(userInfo);


                            mLogin.dismiss();
                            Intent MainIntent=new Intent(CreatAcount.this,MainActivity.class);
                            startActivity(MainIntent);
                            finish();
                        }
                        else
                        {
                            mLogin.hide();
                            Toast.makeText(CreatAcount.this,"you got some error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}
