package es.uniovi.sdm.alvfer.girodeitalia.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.ElementoPatrimonio;
import es.uniovi.sdm.alvfer.girodeitalia.R;

public class ElementoPatrimonioActivity extends AppCompatActivity {

    private TextView textViewNombre;
    private TextView textViewLugar;
    private TextView textViewDescripcion;
    private TextView textViewEtapa;

    private FirebaseStorage firebaseStorage;
    private StorageReference elementosPatrimonioImagenesStorageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elemento_patrimonio);

        textViewNombre = findViewById(R.id.textViewNombre);
        textViewLugar = findViewById(R.id.textViewLugar);
        textViewDescripcion = findViewById(R.id.textViewDescripcion);
        textViewEtapa = findViewById(R.id.textViewEtapa);

        firebaseStorage = FirebaseStorage.getInstance();
        elementosPatrimonioImagenesStorageReference = firebaseStorage.getReference().child
                ("ElementosPatrimonio");

        Bundle b = getIntent().getExtras();
        ElementoPatrimonio elementoPatrimonio = b.getParcelable(PatrimonioActivity
                .OBJETO_ELEMENTO_PATRIMONIO);

        String nombreImagen = elementoPatrimonio.getNombreImagen();
        StorageReference photoRef = elementosPatrimonioImagenesStorageReference.child(nombreImagen);

        ImageView imageView = findViewById(R.id.imageViewPhoto);

        Glide.with(this).load(photoRef).into(imageView);

        textViewNombre.setText(elementoPatrimonio.getNombre());
        textViewDescripcion.setText("Descripción: " + elementoPatrimonio.getDescripcion());
        textViewLugar.setText("Lugar: " + elementoPatrimonio.getLugar());
        textViewEtapa.setText("Etapa: " + elementoPatrimonio.getEtapa());
    }
}
