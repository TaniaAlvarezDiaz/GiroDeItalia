package es.uniovi.sdm.alvfer.girodeitalia.vista.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Ganador;
import es.uniovi.sdm.alvfer.girodeitalia.vista.utilidades.GanadorAdapter;

public class PalmaresActivity extends AppCompatActivity {

    private ArrayList<Ganador> ganadores;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ganadoresDatabaseReference;
    private RecyclerView recyclerView;
    private GanadorAdapter ganadorAdapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palmares);

        progressBar = findViewById(R.id.progressBarPalmares);
        recyclerView = findViewById(R.id.recyclerViewPalmares);

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        ganadores = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ganadoresDatabaseReference = firebaseDatabase.getReference().child("Ganadores");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext
                ());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager
                .VERTICAL));

        ganadorAdapter = new GanadorAdapter(ganadores, getApplicationContext());
        recyclerView.setAdapter(ganadorAdapter);

        obtenerGanadores();
    }


    private void obtenerGanadores() {
        Query queryRef = ganadoresDatabaseReference.orderByChild("year");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Ganador ganador = snapshot.getValue(Ganador
                            .class);
                    ganadores.add(ganador);
                }
                Collections.reverse(ganadores);
                ganadorAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Ha habido un problema al cargar los " +
                        "datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
