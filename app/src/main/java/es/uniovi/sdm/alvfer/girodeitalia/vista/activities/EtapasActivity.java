package es.uniovi.sdm.alvfer.girodeitalia.vista.activities;

import android.content.Intent;
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

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Etapa;
import es.uniovi.sdm.alvfer.girodeitalia.vista.utilidades.EtapaAdapter;
import es.uniovi.sdm.alvfer.girodeitalia.vista.utilidades.RecyclerTouchListener;

public class EtapasActivity extends AppCompatActivity {

    private ArrayList<Etapa> etapas;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference etapasDatabaseReference;
    private RecyclerView recyclerView;
    private EtapaAdapter etapaAdapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapas);

        progressBar = findViewById(R.id.progressBarEtapas);
        recyclerView = findViewById(R.id.recyclerViewEtapas);

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        etapas = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        etapasDatabaseReference = firebaseDatabase.getReference().child("Etapas");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext
                ());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager
                .VERTICAL));

        etapaAdapter = new EtapaAdapter(etapas, getApplicationContext());
        recyclerView.setAdapter(etapaAdapter);

        obtenerEtapas();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Etapa etapa = etapas.get(position);
                Intent intent = new Intent(getApplicationContext(), EtapaActivity.class);
                intent.putExtra(EtapaActivity.ETAPA, etapa);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    private void obtenerEtapas() {
        Query queryRef = etapasDatabaseReference.orderByChild("numero");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Etapa etapa = snapshot.getValue(Etapa
                            .class);
                    etapas.add(etapa);
                }
                etapaAdapter.notifyDataSetChanged();
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
