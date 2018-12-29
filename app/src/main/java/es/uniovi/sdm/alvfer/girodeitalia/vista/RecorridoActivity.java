package es.uniovi.sdm.alvfer.girodeitalia.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

import es.uniovi.sdm.alvfer.girodeitalia.R;

public class RecorridoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido);

        PhotoView photoView = (PhotoView) findViewById(R.id.ivRecorrido);
        //Comentar si se usa Glide
        //photoView.setImageResource(R.drawable.mapa_recorrido);

        Glide.with(this).load(R.drawable.mapa_recorrido).into(photoView);
    }
}
