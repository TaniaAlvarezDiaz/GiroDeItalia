package es.uniovi.sdm.alvfer.girodeitalia.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.ElementoPatrimonio;
import es.uniovi.sdm.alvfer.girodeitalia.R;

public class PatrimonioActivity extends AppCompatActivity {

    public static final String OBJETO_ELEMENTO_PATRIMONIO = "ObjetoElementoPatrimonio";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference elementosPatrimonioDatabaseReference;
    private ArrayList<ElementoPatrimonio> elementosPatrimonio;
    private ListView listViewElementosPatrimonio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimonio);

        listViewElementosPatrimonio = findViewById(R.id.listViewElementosPatrimonio);
        elementosPatrimonio = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        elementosPatrimonioDatabaseReference = firebaseDatabase.getReference().child
                ("ElementosPatrimonio");

        obtenerElementosPatrimonio();


    }

    private void obtenerElementosPatrimonio() {
        Query queryRef = elementosPatrimonioDatabaseReference.orderByChild("nombre");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ElementoPatrimonio elementoPatrimonio = snapshot.getValue(ElementoPatrimonio
                            .class);
                    elementosPatrimonio.add(elementoPatrimonio);
                }
                inicializarListViewElementosPatrimonio();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE", "Ha ocurrido un fallo de lectura.");
            }
        });
    }

    private void inicializarListViewElementosPatrimonio() {

        ArrayAdapter<ElementoPatrimonio> adapter = new ArrayAdapter<>(this, android.R.layout
                .simple_list_item_1, elementosPatrimonio);
        listViewElementosPatrimonio.setAdapter(adapter);

        listViewElementosPatrimonio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ElementoPatrimonio elementoPatrimonio = elementosPatrimonio.get(position);
                Intent intent = new Intent(getApplicationContext(), ElementoPatrimonioActivity
                        .class);
                intent.putExtra(OBJETO_ELEMENTO_PATRIMONIO, elementoPatrimonio);
                startActivity(intent);
                /*Toast.makeText(getApplicationContext(), "Item seleccionado: " + position, Toast
                .LENGTH_SHORT)
                        .show();*/
            }
        });
    }
}
