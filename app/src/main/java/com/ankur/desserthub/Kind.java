package com.ankur.desserthub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;

import javax.annotation.Nullable;


public class Kind extends AppCompatActivity {
    private static final String TAG = "FireLog";
    String Data;
    TextView T1;


    private FirebaseAuth FA;
    private FirebaseFirestore db;
    ArrayList<String> items;
    ArrayList<String> recipes;
    ArrayList<String> times;
    ArrayList<String> ings;
    ArrayAdapter<String> itemadaptor;
    ListView LView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind);
        db = FirebaseFirestore.getInstance();
        FA = FirebaseAuth.getInstance();
        items = new ArrayList<>();
        recipes = new ArrayList<>();
        times = new ArrayList<>();
        ings = new ArrayList<>();

        //array adapter to display listView
        LView = findViewById(R.id.itemList);
        itemadaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        LView.setAdapter(itemadaptor);
        //itemadaptor.add("Cheese");


        //onClick of Items
       LView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //send data through intent
                        String tl=items.get(position);
                        String rp=recipes.get(position);
                        String tm=times.get(position);
                        String ingr=ings.get(position);
                        Intent intent = new Intent(Kind.this, SeeRecipe.class);
                        intent.putExtra("title", tl);
                        intent.putExtra("recipe", rp);
                        intent.putExtra("time", tm);
                        intent.putExtra("ingredient", ingr);
                        startActivity(intent);

                    }
                }
        );


        //for test
        T1 = findViewById(R.id.t1);


        //retrieving data from intent
        Data = getIntent().getStringExtra("data");
        T1.setText(Data);
        if (Data.equals("Cakes")) {
            getAllCakes();
        } else if (Data.equals("Cookies")) {
            getAllCookies();
        } else {
            getAllPies();
        }



    }



    public void getAllCakes(){
        //then retrieve all from cakes collection
        db.collection("cakes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error" + e.getMessage());
                    return;
                }
                String tl = "";
                String rp = "";
                String tm = "";
                String in = "";
                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType()==DocumentChange.Type.ADDED) {
                        tl = doc.getDocument().getString("title");
                        rp = doc.getDocument().getString("recipe");
                        tm = doc.getDocument().getString("time");
                        in = doc.getDocument().getString("ingredient");
                        items.add(tl);
                        recipes.add(rp);
                        times.add(tm);
                        ings.add(in);
                        itemadaptor.notifyDataSetChanged();
                        Log.d(TAG, "Title: " + tl + "\n Recipe: " +rp);


                    }

                }
                //T1.setText(tl);
            }


        });
    }

    public void getAllCookies(){
        //then retrieve all from cookies collection
        db.collection("cookies").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error" + e.getMessage());
                    return;
                }
                String tl = "";
                String rp = "";
                String tm = "";
                String in = "";
                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType()==DocumentChange.Type.ADDED) {
                        tl = doc.getDocument().getString("title");
                        rp = doc.getDocument().getString("recipe");
                        tm = doc.getDocument().getString("time");
                        in = doc.getDocument().getString("ingredient");
                        items.add(tl);
                        recipes.add(rp);
                        times.add(tm);
                        ings.add(in);
                        itemadaptor.notifyDataSetChanged();
                        Log.d(TAG, "Title: " + tl);
                    }

                }
                //T1.setText(tl);
            }


        });
    }


    public void getAllPies(){
        //then retrieve all from pies collection
        db.collection("pies").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error" + e.getMessage());
                    return;
                }
                String tl = "";
                String rp = "";
                String tm = "";
                String in = "";
                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType()==DocumentChange.Type.ADDED) {
                        tl = doc.getDocument().getString("title");
                        rp = doc.getDocument().getString("recipe");
                        tm = doc.getDocument().getString("time");
                        in = doc.getDocument().getString("ingredient");
                        items.add(tl);
                        recipes.add(rp);
                        times.add(tm);
                        ings.add(in);
                        itemadaptor.notifyDataSetChanged();
                        Log.d(TAG, "Title: " + tl);
                    }

                }
                //T1.setText(tl);
            }


        });
    }



    //menu
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
