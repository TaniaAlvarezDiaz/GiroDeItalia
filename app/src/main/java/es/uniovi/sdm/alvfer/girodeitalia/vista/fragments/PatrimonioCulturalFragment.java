package es.uniovi.sdm.alvfer.girodeitalia.vista.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.ElementoPatrimonio;
import es.uniovi.sdm.alvfer.girodeitalia.vista.activities.ElementoPatrimonioActivity;
import es.uniovi.sdm.alvfer.girodeitalia.vista.activities.PatrimonioActivity;

public class PatrimonioCulturalFragment extends ListFragment {

    private ArrayList<ElementoPatrimonio> elementosPatrimonioCultural;
    private ArrayAdapter<ElementoPatrimonio> arrayAdapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference elementosPatrimonioDatabaseReference;

    public PatrimonioCulturalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            ((PatrimonioActivity) getActivity()).getProgressBar().setVisibility(View.VISIBLE);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        elementosPatrimonioDatabaseReference = firebaseDatabase.getReference().child
                ("ElementosPatrimonio");
        elementosPatrimonioCultural = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout
                .simple_list_item_1, elementosPatrimonioCultural);
        setListAdapter(arrayAdapter);
        obtenerElementosPatrimonioCultural();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ElementoPatrimonio elementoPatrimonio = elementosPatrimonioCultural.get(position);
        Intent intent = new Intent(getActivity().getApplicationContext(), ElementoPatrimonioActivity
                .class);
        intent.putExtra(ElementoPatrimonioActivity.ELEMENTO_PATRIMONIO, elementoPatrimonio);
        startActivity(intent);
    }


    private void obtenerElementosPatrimonioCultural() {
        Query queryRef = elementosPatrimonioDatabaseReference.orderByChild("tipoPatrimonio")
                .equalTo("cultural");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ElementoPatrimonio elementoPatrimonio = snapshot.getValue(ElementoPatrimonio
                            .class);
                    elementosPatrimonioCultural.add(elementoPatrimonio);
                }
                Collections.sort(elementosPatrimonioCultural, new Comparator<ElementoPatrimonio>() {
                    @Override
                    public int compare(ElementoPatrimonio ep1, ElementoPatrimonio ep2) {
                        return ep1.getNombre().compareToIgnoreCase(ep2.getNombre());
                    }
                });
                if (getActivity() != null) {
                    ((PatrimonioActivity) getActivity()).getProgressBar().setVisibility(View.GONE);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), "Ha habido un problema al " +
                        "cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
