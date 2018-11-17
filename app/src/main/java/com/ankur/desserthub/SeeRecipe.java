package com.ankur.desserthub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SeeRecipe extends AppCompatActivity {
    private String ttl;
    private String tme;
    private String ing;
    private String rcp;
    private String totalrcp;
    private TextView T1;
    private TextView T2;
    private FirebaseAuth FA=FirebaseAuth.getInstance();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_recipe);

        //data from intent
        ttl = getIntent().getStringExtra("title");
        rcp = getIntent().getStringExtra("recipe");
        tme = getIntent().getStringExtra("time");
        ing = getIntent().getStringExtra("ingredient");
        totalrcp = "Ingredients: \n"+ing+"\n"+"\n Method: \n"+rcp+"\n"+"\n Time required: \n"+tme+" minutes";

        //displaying the data
        T1=findViewById(R.id.seetitle);
        T2=findViewById(R.id.seerecipe);
        T1.setText(ttl);
        T2.setText(totalrcp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.first_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.lg){
            logOut();
        }

        if(id==R.id.myrecp){
            addNew();
        }


        if(id==R.id.share){
            shareRecipe();
        }


        return super.onOptionsItemSelected(item);
    }

    //go to timer activity
    public void gotoTimer (View view){

        Intent intent = new Intent(SeeRecipe.this, MyTime.class);
        intent.putExtra("time", tme);
        startActivity(intent);

    }




    public void logOut(){
        FA.signOut();
        finish();
        startActivity(new Intent(this, Login.class));
    }


    public void addNew (){

        onPause();
        startActivity(new Intent(getApplicationContext(), AddRecipe.class));
    }

    public void shareRecipe(){
        Intent i = new Intent(Intent.ACTION_SEND);
        String body=ttl+"\n"+"\n"+totalrcp;
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(i, "Share Via"));

    }


}
