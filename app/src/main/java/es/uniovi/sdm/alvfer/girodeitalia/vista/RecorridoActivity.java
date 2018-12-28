package es.uniovi.sdm.alvfer.girodeitalia.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import es.uniovi.sdm.alvfer.girodeitalia.R;

public class RecorridoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido);
        ImageView imageView = findViewById(R.id.ivRecorrido);
        Glide.with(this).load(R.drawable.mapa_recorrido).into(imageView);
    }
}
