package es.uniovi.sdm.alvfer.girodeitalia.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.ElementoPatrimonio;


public class NuevaPatrimonioActivity extends AppCompatActivity {

    public static final String ELEMENTOS_PATRIMONIO_HISTORICO = "ElementosPatrimonioHistorico";
    public static final String ELEMENTO_PATRIMONIO = "ElementoPatrimonio";

    private TextView mTextMessage;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference elementosPatrimonioDatabaseReference;
    private ArrayList<ElementoPatrimonio> elementosPatrimonio;
    private ListView listViewElementosPatrimonio;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    PatrimonioHistoricoFragment patrimonioHistoricoFragment = new PatrimonioHistoricoFragment();
                    Bundle arguments = new Bundle();
                    arguments.putParcelableArrayList(ELEMENTOS_PATRIMONIO_HISTORICO, NuevaPatrimonioActivity.this.elementosPatrimonio);
                    Log.d("ARGUMENTS", "Elementos del patrimonio enviados al fragment: " + NuevaPatrimonioActivity.this.elementosPatrimonio);
                    patrimonioHistoricoFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, patrimonioHistoricoFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_patrimonio);

        mTextMessage = (TextView) findViewById(R.id.message);
        elementosPatrimonio = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        elementosPatrimonioDatabaseReference = firebaseDatabase.getReference().child
                ("ElementosPatrimonio");
        obtenerElementosPatrimonio();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                //inicializarListViewElementosPatrimonio();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE", "Ha ocurrido un fallo de lectura.");
            }
        });
    }

}
