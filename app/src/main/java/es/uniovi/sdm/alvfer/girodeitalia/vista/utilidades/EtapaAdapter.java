package es.uniovi.sdm.alvfer.girodeitalia.vista.utilidades;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Etapa;

public class EtapaAdapter extends RecyclerView.Adapter<EtapaAdapter.MyViewHolder> {

    private List<Etapa> etapas;
    private FirebaseStorage firebaseStorage;
    private StorageReference iconosTipoEtapaStorageReference;
    private Context context;

    public EtapaAdapter(List<Etapa> etapas, Context context) {
        firebaseStorage = FirebaseStorage.getInstance();
        iconosTipoEtapaStorageReference = firebaseStorage.getReference().child
                ("IconosTipoEtapa");
        this.context = context;
        this.etapas = etapas;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView numero, kilometros;
        public ImageView tipo;

        public MyViewHolder(View itemView) {
            super(itemView);
            numero = itemView.findViewById(R.id.textViewNumeroEtapaRecycler);
            kilometros = itemView.findViewById(R.id.textViewKilometrosEtapaRecycler);
            tipo = itemView.findViewById(R.id.imageViewTipoEtapaRecycler);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .etapa_recycler_view_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Etapa etapa = etapas.get(position);
        String nombreImagenTipoEtapa = "";
        String tipoEtapa = etapa.getTipo();
        if (tipoEtapa.equals("Llana")) {
            nombreImagenTipoEtapa = "llana.png";
        } else if (tipoEtapa.equals("Media montaña")) {
            nombreImagenTipoEtapa = "mediaMontana.png";
        } else if (tipoEtapa.equals("Alta montaña")) {
            nombreImagenTipoEtapa = "altaMontana.png";
        } else if (tipoEtapa.equals("Contrarreloj individual")) {
            nombreImagenTipoEtapa = "contrarreloj.png";
        }
        StorageReference photoRef = iconosTipoEtapaStorageReference.child(nombreImagenTipoEtapa);
        Glide.with(context).load(photoRef).into(holder.tipo);
        holder.numero.setText("Etapa " + Integer.valueOf(etapa.getNumero()).toString());
        holder.kilometros.setText(Integer.valueOf(etapa.getKilometros()).toString() + " km");
    }

    @Override
    public int getItemCount() {
        return etapas.size();
    }
}

