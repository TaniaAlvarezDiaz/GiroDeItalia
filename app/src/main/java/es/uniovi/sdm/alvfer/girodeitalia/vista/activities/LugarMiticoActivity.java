package es.uniovi.sdm.alvfer.girodeitalia.vista.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.ElementoPatrimonio;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.LugarMitico;

public class LugarMiticoActivity extends AppCompatActivity {

    public static final String LUGAR_MITICO = "LugarMitico";

    private TextView textViewNombre;
    private TextView textViewDescripcion;
    private TextView textViewHechosHistoricos;

    private FirebaseStorage firebaseStorage;
    private StorageReference lugaresMiticosImagenesStorageReference;

    private LugarMitico lugarMitico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_mitico);

        textViewNombre = findViewById(R.id.textViewNombreLugarMitico);
        textViewDescripcion = findViewById(R.id.textViewDescripcionLugarMitico);
        textViewHechosHistoricos = findViewById(R.id.textViewHechosHistoricos);

        firebaseStorage = FirebaseStorage.getInstance();
        lugaresMiticosImagenesStorageReference = firebaseStorage.getReference().child
                ("LugaresMiticos");

        Bundle b = getIntent().getExtras();

        lugarMitico = b.getParcelable(LUGAR_MITICO);

        String nombreImagen = lugarMitico.getNombreImagen();
        StorageReference photoRef = lugaresMiticosImagenesStorageReference.child(nombreImagen);

        ImageView imageView = findViewById(R.id.imageViewPhotoLugarMitico);

        Glide.with(this).load(photoRef).apply(new RequestOptions().error(R.drawable.error_imagen)).into(imageView);

        textViewNombre.setText(lugarMitico.getNombre());
        textViewDescripcion.setText(lugarMitico.getDescripcion());
        textViewHechosHistoricos.setText(lugarMitico.getHechosHistoricos());
    }

    public void verEnMapa(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("nombre", lugarMitico.getNombre());
        intent.putExtra("latitud", lugarMitico.getLatitud());
        intent.putExtra("longitud", lugarMitico.getLongitud());
        startActivity(intent);
    }
}
