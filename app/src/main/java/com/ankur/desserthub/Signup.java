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

public class Signup extends AppCompatActivity implements View.OnClickListener{

    private EditText UEmail;
    private EditText UPass;
    private TextView LI;
    private Button B1;
    private ProgressDialog pd;
    private FirebaseAuth FA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        FA=FirebaseAuth.getInstance();

        if(FA.getCurrentUser() != null){
            //if user logged in
            finish();
            startActivity(new Intent(getApplicationContext(),Category.class));
        }

        pd=new ProgressDialog(this);
        UEmail=(EditText) findViewById(R.id.etemail);
        UPass =(EditText) findViewById(R.id.etpass);
        LI=(TextView) findViewById(R.id.tvlogin);
        B1=(Button) findViewById(R.id.signup);

        B1.setOnClickListener(this);
        LI.setOnClickListener(this);
    }


    public void signUp(){
        String email=UEmail.getText().toString().trim();
        String pass=UPass.getText().toString().trim();

        //checking if empty field
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Must enter email to sign up", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Must enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        pd.setMessage("Registering user...");
        pd.show();
        FA.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pd.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),Login.class));

                        }
                        else{
                            Toast.makeText(Signup.this, "Something went wrong.Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view==B1){
            signUp();
        }
        if(view==LI){
            finish();
            startActivity(new Intent(this, Login.class));
        }

    }
}
