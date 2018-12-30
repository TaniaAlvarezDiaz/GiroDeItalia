package es.uniovi.sdm.alvfer.girodeitalia.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import es.uniovi.sdm.alvfer.girodeitalia.R;

public class RecorridoActivity extends AppCompatActivity {

    private FirebaseStorage firebaseStorage;
    private StorageReference mapaRecorridoImagenesStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido);

        PhotoView photoView = (PhotoView) findViewById(R.id.ivRecorrido);

        //Comentar si se usa Glide
        //photoView.setImageResource(R.drawable.mapa_recorrido);

        //Glide.with(this).load(R.drawable.mapa_recorrido).into(photoView);
        //Picasso.get().load(R.drawable.mapa_recorrido).into(photoView);


        firebaseStorage = FirebaseStorage.getInstance();
        mapaRecorridoImagenesStorageReference = firebaseStorage.getReference().child
                ("MapaRecorrido");
        StorageReference photoRef = mapaRecorridoImagenesStorageReference.child("MapaRecorrido.png");
        Glide.with(this).load(photoRef).into(photoView);

    }
}
