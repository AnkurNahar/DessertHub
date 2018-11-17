package com.ankur.desserthub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Category extends AppCompatActivity {

    private FirebaseAuth FA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        FA= FirebaseAuth.getInstance();
        if(FA.getCurrentUser() == null){
            //user not logged in
            finish();
            startActivity(new Intent(this,Login.class));
        }

        FirebaseUser user=FA.getCurrentUser();
        //display user data


    }





    public void gotoCakes(View view){

        String userData = "Cakes";
        Intent intent = new Intent(Category.this, Kind.class);
        intent.putExtra("data", userData);
        startActivity(intent);

    }

    public void gotoCookies(View view){

        String userData = "Cookies";
        Intent intent = new Intent(Category.this, Kind.class);
        intent.putExtra("data", userData);
        startActivity(intent);

    }

    public void gotoPies(View view){

        String userData = "Pies";
        Intent intent = new Intent(Category.this, Kind.class);
        intent.putExtra("data", userData);
        startActivity(intent);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.second_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.lg2){
            logOut();
        }

        if(id==R.id.myrecp2){
            addNew();
        }


        return super.onOptionsItemSelected(item);
    }




    public void addNew (){
        onPause();
        startActivity(new Intent(getApplicationContext(), AddRecipe.class));
    }




    public void logOut(){
        FA.signOut();
        finish();
        startActivity(new Intent(this, Login.class));
    }



}
