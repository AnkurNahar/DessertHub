package com.ankur.desserthub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText UEmail0;
    private EditText UPass0;
    private TextView SU;
    private Button B2;
    private ProgressDialog pd;
    private FirebaseAuth FA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FA = FirebaseAuth.getInstance();
        if (FA.getCurrentUser() != null) {
            //if user logged in
            finish();
            startActivity(new Intent(getApplicationContext(), Category.class));
        }



        pd=new ProgressDialog(this);
        UEmail0=(EditText) findViewById(R.id.etemail0);
        UPass0 =(EditText) findViewById(R.id.etpass0);
        SU=(TextView) findViewById(R.id.tvsignup);
        B2=(Button) findViewById(R.id.login);


        B2.setOnClickListener(this);
        SU.setOnClickListener(this);

    }



    private void userLogin(){
        String email=UEmail0.getText().toString().trim();
        String pass=UPass0.getText().toString().trim();

        //checking if empty field
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Must enter email to login", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Must enter your password", Toast.LENGTH_SHORT).show();
            return;
        }


        pd.setMessage("Logging in...");
        pd.show();
        FA.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pd.dismiss();
                        if(task.isSuccessful()){
                            //start next activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Category.class));

                        }
                        else{
                            Toast.makeText(Login.this, "Something went wrong.Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view==B2){
            userLogin();
        }
        if(view==SU){
            finish();
            startActivity(new Intent(this, Signup.class));

        }


    }
}
