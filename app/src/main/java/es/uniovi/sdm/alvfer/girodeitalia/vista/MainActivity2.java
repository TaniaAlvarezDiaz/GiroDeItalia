package es.uniovi.sdm.alvfer.girodeitalia.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import es.uniovi.sdm.alvfer.girodeitalia.R;

public class MainActivity2 extends AppCompatActivity {

    private TextView textTitulo;
    private TextView textViewOpcionEscogida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        textTitulo = (TextView) findViewById(R.id.txTitulo);
        textViewOpcionEscogida = (TextView) findViewById(R.id.txOpcionEscogida);

        Intent intent = getIntent();
        //Recuperamos la variable
        String message = intent.getStringExtra(MainActivity.OPCION_ESCOGIDA);

        //Modificamos el texto
        textViewOpcionEscogida.setText(message);

    }
}
