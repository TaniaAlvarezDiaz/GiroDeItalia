package es.uniovi.sdm.alvfer.girodeitalia.vista.activities;

import android.app.ProgressDialog;
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
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Dia;
import es.uniovi.sdm.alvfer.girodeitalia.vista.utilidades.CalendarioAdapter;

public class CalendarioActivity extends AppCompatActivity {

    private ArrayList<Dia> dias;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference calendarioDatabaseReference;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CalendarioAdapter calendarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        progressBar = findViewById(R.id.progressBarCalendario);
        recyclerView = findViewById(R.id.recyclerViewCalendario);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        dias = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        calendarioDatabaseReference = firebaseDatabase.getReference().child("Calendario");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        calendarioAdapter = new CalendarioAdapter(dias);
        recyclerView.setAdapter(calendarioAdapter);

        obtenerDias();
    }

    private void obtenerDias() {
        Query queryRef = calendarioDatabaseReference.orderByChild("id");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Dia dia = snapshot.getValue(Dia.class);
                    dias.add(dia);
                    calendarioAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE", "Ha ocurrido un fallo de lectura.");
                Toast.makeText(getApplicationContext(), "Ha habido un problema al cargar los datos",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
