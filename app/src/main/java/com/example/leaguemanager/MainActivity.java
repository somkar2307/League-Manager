package com.example.leaguemanager;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://leaguemanager-9cc5c-default-rtdb.firebaseio.com/");
RecyclerView recycler_view;
ModelAdapter adapter;
    List<Model> list=new ArrayList<>();
    Model m=new Model();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view=findViewById(R.id.recycler_view);
        setRecyclerView();
    }

    private void setRecyclerView() {
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

            String date,m1,m2;

            databaseReference.child("matches").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String date = snapshot.child("1").child("date").getValue(String.class);
                    String m1=snapshot.child("1").child("team1").getValue(String.class);
                    String m2=snapshot.child("1").child("team2").getValue(String.class);
                    m.setDate(date);
                    m.setTeam1(m1);
                    m.setTeam2(m2);

                    //  list.add(new Model("1","Pargol","Onkar"));
                    // list.add(new Model("1","Pargol","Onkar"));

                }




                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        list.add(m);
        adapter=new ModelAdapter(this,list);
        recycler_view.setAdapter(adapter);
    }

    }