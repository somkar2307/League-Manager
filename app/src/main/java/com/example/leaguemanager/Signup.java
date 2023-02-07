package com.example.leaguemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Signup extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://leaguemanager-9cc5c-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(Signup.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
    }

    public void registerUser(View view){
        EditText fullName=findViewById(R.id.name);
        EditText userName=findViewById(R.id.username);
        EditText password=findViewById(R.id.password);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);


        String name=fullName.getText().toString();
        String uname= userName.getText().toString();
        String pass=password.getText().toString();
        String text = mySpinner.getSelectedItem().toString();

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(uname))
                {

                }
                else{
                    int count=0;
                    for(DataSnapshot ds : snapshot.getChildren()) {
                        String team = ds.child("team").getValue(String.class);
                        if(team.equals(text)){count++;}
                    }
                    if(count>=2){
                        Toast.makeText(Signup.this,"Team Count greater than 5",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        databaseReference.child("users").child(uname).child("fullname").setValue(name);
                        databaseReference.child("users").child(uname).child("username").setValue(uname);
                        databaseReference.child("users").child(uname).child("password").setValue(pass);
                        databaseReference.child("users").child(uname).child("team").setValue(text);
                        Toast.makeText(Signup.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        finish();

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}