package es.uniovi.sdm.alvfer.girodeitalia.vista;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import es.uniovi.sdm.alvfer.girodeitalia.R;


public class PatrimonioActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    PatrimonioHistoricoFragment patrimonioHistoricoFragment = new
                            PatrimonioHistoricoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id
                            .fragment_container, patrimonioHistoricoFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    PatrimonioCulturalFragment patrimonioCulturalFragment = new
                            PatrimonioCulturalFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id
                            .fragment_container, patrimonioCulturalFragment).commit();
                    return true;
                case R.id.navigation_notifications:
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

        PatrimonioHistoricoFragment patrimonioHistoricoFragment = new
                PatrimonioHistoricoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id
                .fragment_container, patrimonioHistoricoFragment).commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
