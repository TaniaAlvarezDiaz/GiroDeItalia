package es.uniovi.sdm.alvfer.girodeitalia.vista.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.LugarMitico;

public class LugaresMiticosActivity extends AppCompatActivity {

    public static ArrayList<LugarMitico> lugaresMiticos;
    public ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference lugaresMiticosDatabaseReference;
    private ProgressBar progressBar;
    private ArrayAdapter<LugarMitico> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_miticos);

        progressBar = findViewById(R.id.progressBarLugaresMiticos);
        listView = findViewById(R.id.listViewLugaresMiticos);

        progressBar.setVisibility(View.VISIBLE);

        lugaresMiticos = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        lugaresMiticosDatabaseReference = firebaseDatabase.getReference().child("LugaresMiticos");

        adapter = new ArrayAdapter<LugarMitico>(this, android.R.layout.simple_list_item_1, lugaresMiticos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LugarMitico lugarMitico = lugaresMiticos.get(position);
                Intent intent = new Intent(getApplicationContext(), LugarMiticoActivity.class);
                intent.putExtra(LugarMiticoActivity.LUGAR_MITICO, lugarMitico);
                startActivity(intent);
            }
        });

        obtenerLugaresMiticos();
    }


    private void obtenerLugaresMiticos() {
        Query queryRef = lugaresMiticosDatabaseReference.orderByChild("nombre");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LugarMitico lugarMitico = snapshot.getValue(LugarMitico
                            .class);
                    lugaresMiticos.add(lugarMitico);
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Ha habido un problema al cargar los " +
                        "datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
