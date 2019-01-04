package es.uniovi.sdm.alvfer.girodeitalia.vista.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.ElementoPatrimonio;
import es.uniovi.sdm.alvfer.girodeitalia.R;

public class ElementoPatrimonioActivity extends AppCompatActivity {

    public static final String ELEMENTO_PATRIMONIO = "ElementoPatrimonio";

    private TextView textViewNombre;
    private TextView textViewLugar;
    private TextView textViewDescripcion;
    private TextView textViewEtapa;

    private FirebaseStorage firebaseStorage;
    private StorageReference elementosPatrimonioImagenesStorageReference;

    private ElementoPatrimonio elementoPatrimonio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elemento_patrimonio);

        textViewNombre = findViewById(R.id.textViewNombreElemento);
        textViewLugar = findViewById(R.id.textViewLugarElemento);
        textViewDescripcion = findViewById(R.id.textViewDescripcionElemento);
        textViewEtapa = findViewById(R.id.textViewEtapaElemento);

        firebaseStorage = FirebaseStorage.getInstance();
        elementosPatrimonioImagenesStorageReference = firebaseStorage.getReference().child
                ("ElementosPatrimonio");

        Bundle b = getIntent().getExtras();

        elementoPatrimonio = b.getParcelable(ELEMENTO_PATRIMONIO);

        String nombreImagen = elementoPatrimonio.getNombreImagen();
        StorageReference photoRef = elementosPatrimonioImagenesStorageReference.child(nombreImagen);

        ImageView imageView = findViewById(R.id.imageViewPhotoElementoPat);

        Glide.with(this).load(photoRef).apply(new RequestOptions().error(R.drawable.error_imagen)).into(imageView);

        textViewNombre.setText(elementoPatrimonio.getNombre());
        textViewDescripcion.setText(elementoPatrimonio.getDescripcion());
        textViewLugar.setText(elementoPatrimonio.getLugar());
        textViewEtapa.setText("" + elementoPatrimonio.getEtapa());
    }

    public void verEnMapa(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("nombre", elementoPatrimonio.getNombre());
        intent.putExtra("latitud", elementoPatrimonio.getLatitud());
        intent.putExtra("longitud", elementoPatrimonio.getLongitud());
        startActivity(intent);
    }
}
