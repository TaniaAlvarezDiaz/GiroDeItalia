package es.uniovi.sdm.alvfer.girodeitalia.vista.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Etapa;
import es.uniovi.sdm.alvfer.girodeitalia.vista.fragments.ElementosPatrimonioEtapaFragment;
import es.uniovi.sdm.alvfer.girodeitalia.vista.fragments.InformacionEtapaFragment;

public class EtapaActivity extends AppCompatActivity {

    public static final String ETAPA = "Etapa";

    private Etapa etapa;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(ETAPA, etapa);
            switch (item.getItemId()) {
                case R.id.navigation_datos:
                    InformacionEtapaFragment informacionEtapaFragment = new InformacionEtapaFragment();
                    informacionEtapaFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction().replace(R.id
                            .etapa_fragment_container, informacionEtapaFragment).commit();
                    return true;
                case R.id.navigation_patrimonio:
                    ElementosPatrimonioEtapaFragment elementosPatrimonioEtapaFragment = new ElementosPatrimonioEtapaFragment();
                    elementosPatrimonioEtapaFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction().replace(R.id
                            .etapa_fragment_container, elementosPatrimonioEtapaFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa);

        Bundle b = getIntent().getExtras();
        this.etapa = b.getParcelable(ETAPA);

        InformacionEtapaFragment informacionEtapaFragment = new InformacionEtapaFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ETAPA, etapa);
        informacionEtapaFragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id
                .etapa_fragment_container, informacionEtapaFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationActivityEtapa);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
