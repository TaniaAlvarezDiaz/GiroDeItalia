package es.uniovi.sdm.alvfer.girodeitalia.vista.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
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
import es.uniovi.sdm.alvfer.girodeitalia.vista.activities.ElementoPatrimonioActivity;

public class PatrimonioGeograficoFragment extends ListFragment {

    private ArrayList<ElementoPatrimonio> elementosPatrimonioGeografico;
    private ArrayAdapter<ElementoPatrimonio> arrayAdapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference elementosPatrimonioDatabaseReference;

    public PatrimonioGeograficoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        elementosPatrimonioDatabaseReference = firebaseDatabase.getReference().child
                ("ElementosPatrimonio");
        elementosPatrimonioGeografico = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout
                .simple_list_item_1, elementosPatrimonioGeografico);
        setListAdapter(arrayAdapter);
        obtenerElementosPatrimonioGeografico();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ElementoPatrimonio elementoPatrimonio = elementosPatrimonioGeografico.get(position);
        Intent intent = new Intent(getActivity().getApplicationContext(), ElementoPatrimonioActivity
                .class);
        intent.putExtra(ElementoPatrimonioActivity.ELEMENTO_PATRIMONIO, elementoPatrimonio);
        startActivity(intent);
    }


    private void obtenerElementosPatrimonioGeografico() {
        Query queryRef = elementosPatrimonioDatabaseReference.orderByChild("tipoPatrimonio")
                .equalTo("geografico");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ElementoPatrimonio elementoPatrimonio = snapshot.getValue(ElementoPatrimonio
                            .class);
                    elementosPatrimonioGeografico.add(elementoPatrimonio);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE", "Ha ocurrido un fallo de lectura.");
            }
        });
    }

}
