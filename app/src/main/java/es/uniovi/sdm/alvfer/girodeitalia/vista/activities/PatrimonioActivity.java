package es.uniovi.sdm.alvfer.girodeitalia.vista.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ProgressBar;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.vista.fragments.PatrimonioCulturalFragment;
import es.uniovi.sdm.alvfer.girodeitalia.vista.fragments.PatrimonioGeograficoFragment;
import es.uniovi.sdm.alvfer.girodeitalia.vista.fragments.PatrimonioHistoricoFragment;


public class PatrimonioActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_historico:
                    PatrimonioHistoricoFragment patrimonioHistoricoFragment = new
                            PatrimonioHistoricoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id
                            .fragment_container, patrimonioHistoricoFragment).commit();
                    return true;
                case R.id.navigation_cultural:
                    PatrimonioCulturalFragment patrimonioCulturalFragment = new
                            PatrimonioCulturalFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id
                            .fragment_container, patrimonioCulturalFragment).commit();
                    return true;
                case R.id.navigation_geografico:
                    PatrimonioGeograficoFragment patrimonioGeograficoFragment = new
                            PatrimonioGeograficoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id
                            .fragment_container, patrimonioGeograficoFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimonio);

        progressBar = findViewById(R.id.progressBarPatrimonioActivity);

        PatrimonioHistoricoFragment patrimonioHistoricoFragment = new
                PatrimonioHistoricoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id
                .fragment_container, patrimonioHistoricoFragment).commit();

        BottomNavigationView navigation = findViewById(R.id.navigationActivityPatrimonio);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

}
