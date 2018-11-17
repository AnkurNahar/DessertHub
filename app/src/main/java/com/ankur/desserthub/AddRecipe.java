package com.ankur.desserthub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddRecipe extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private EditText ing1;
    private EditText tm;
    private Spinner spin;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        et1=findViewById(R.id.e1);
        et2=findViewById(R.id.e2);
        ing1=findViewById(R.id.ing);
        tm=findViewById(R.id.etime);
        spin=findViewById(R.id.spin1);
    }

    public void addRecipe(View view){
        String title=et1.getText().toString();
        String recipe=et2.getText().toString();
        String time=tm.getText().toString();
        String myIng=ing1.getText().toString();
        String cat=spin.getSelectedItem().toString();


        // Add a new document with a generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("title", title);
        data.put("recipe", recipe);
        data.put("time", time);
        data.put("ingredient", myIng);

        if(cat.equals("cakes"))
        {
            db.collection("cakes")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddRecipe.this, "Added", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddRecipe.this, "Error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        else if(cat.equals("cookies")){
            db.collection("cookies")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddRecipe.this, "Added", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddRecipe.this, "Error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        else{
            db.collection("pies")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddRecipe.this, "Added", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddRecipe.this, "Error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        finish();

    }
}
