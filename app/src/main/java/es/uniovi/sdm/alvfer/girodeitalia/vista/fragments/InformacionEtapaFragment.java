package es.uniovi.sdm.alvfer.girodeitalia.vista.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Etapa;
import es.uniovi.sdm.alvfer.girodeitalia.vista.activities.EtapaActivity;


public class InformacionEtapaFragment extends Fragment {

    private FirebaseStorage firebaseStorage;
    private StorageReference etapasImagenesStorageReference;
    private Etapa etapa;
    private TextView textViewNumero;
    private TextView textViewSalida;
    private TextView textViewMeta;
    private TextView textViewKilometros;
    private TextView textViewTipo;
    private TextView textViewFecha;

    public InformacionEtapaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.etapa =  arguments.getParcelable(EtapaActivity.ETAPA);
        }
        firebaseStorage = FirebaseStorage.getInstance();
        etapasImagenesStorageReference = firebaseStorage.getReference().child("Etapas");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_informacion_etapa, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        PhotoView photoViewRecorridoEtapa = getActivity().findViewById(R.id.photoViewRecorridoEtapa);
        PhotoView photoViewPerfilEtapa = getActivity().findViewById(R.id.photoViewPerfilEtapa);

        StorageReference recorridoEtapaPhotoRef = etapasImagenesStorageReference.child("Etapa" + etapa.getNumero() + "Recorrido.jpg");
        Glide.with(this).load(recorridoEtapaPhotoRef).apply(new RequestOptions().error(R.drawable.error_imagen)).into(photoViewRecorridoEtapa);

        StorageReference perfilEtapaPhotoRef = etapasImagenesStorageReference.child("Etapa" + etapa.getNumero() + "Perfil.jpg");
        Glide.with(this).load(perfilEtapaPhotoRef).apply(new RequestOptions().error(R.drawable.error_imagen)).into(photoViewPerfilEtapa);

        textViewNumero = getActivity().findViewById(R.id.textViewNumero);
        textViewNumero.setText("Etapa " + etapa.getNumero());
        textViewSalida = getActivity().findViewById(R.id.textViewSalida);
        textViewSalida.setText("Salida: " + etapa.getSalida());
        textViewMeta = getActivity().findViewById(R.id.textViewMeta);
        textViewMeta.setText("Meta: " + etapa.getMeta());
        textViewKilometros = getActivity().findViewById(R.id.textViewKilometros);
        textViewKilometros.setText("Kilómetros: " + etapa.getKilometros());
        textViewTipo = getActivity().findViewById(R.id.textViewTipo);
        textViewTipo.setText("Tipo: " + etapa.getTipo());
        textViewFecha = getActivity().findViewById(R.id.textViewFecha);
        textViewFecha.setText("Fecha: " + etapa.getFecha());
    }

}
