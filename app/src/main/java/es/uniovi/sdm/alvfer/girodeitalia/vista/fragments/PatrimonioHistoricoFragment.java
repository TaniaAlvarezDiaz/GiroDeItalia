package es.uniovi.sdm.alvfer.girodeitalia.vista.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
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
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.ElementoPatrimonio;
import es.uniovi.sdm.alvfer.girodeitalia.vista.activities.ElementoPatrimonioActivity;
import es.uniovi.sdm.alvfer.girodeitalia.vista.activities.PatrimonioActivity;

public class PatrimonioHistoricoFragment extends ListFragment {

    private ArrayList<ElementoPatrimonio> elementosPatrimonioHistorico;
    private ArrayAdapter<ElementoPatrimonio> arrayAdapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference elementosPatrimonioDatabaseReference;

    public PatrimonioHistoricoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PatrimonioActivity) getActivity()).getProgressBar().setVisibility(View.VISIBLE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        elementosPatrimonioDatabaseReference = firebaseDatabase.getReference().child
                ("ElementosPatrimonio");
        elementosPatrimonioHistorico = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout
                .simple_list_item_1, elementosPatrimonioHistorico);
        setListAdapter(arrayAdapter);
        obtenerElementosPatrimonioHistorico();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ElementoPatrimonio elementoPatrimonio = elementosPatrimonioHistorico.get(position);
        Intent intent = new Intent(getActivity().getApplicationContext(), ElementoPatrimonioActivity
                .class);
        intent.putExtra(ElementoPatrimonioActivity.ELEMENTO_PATRIMONIO, elementoPatrimonio);
        startActivity(intent);
    }


    private void obtenerElementosPatrimonioHistorico() {
        Query queryRef = elementosPatrimonioDatabaseReference.orderByChild("tipoPatrimonio")
                .equalTo("historico");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ElementoPatrimonio elementoPatrimonio = snapshot.getValue(ElementoPatrimonio
                            .class);
                    elementosPatrimonioHistorico.add(elementoPatrimonio);
                }
                ((PatrimonioActivity) getActivity()).getProgressBar().setVisibility(View.GONE);
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
