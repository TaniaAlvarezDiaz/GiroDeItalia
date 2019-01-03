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
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Ganador;

public class GanadorAdapter extends RecyclerView.Adapter<GanadorAdapter.MyViewHolder> {

    private List<Ganador> ganadores;
    private FirebaseStorage firebaseStorage;
    private StorageReference banderasStorageReference;
    private Context context;

    public GanadorAdapter(List<Ganador> ganadores, Context context) {
        firebaseStorage = FirebaseStorage.getInstance();
        banderasStorageReference = firebaseStorage.getReference().child
                ("Banderas");
        this.context = context;
        this.ganadores = ganadores;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView year, nombre;
        public ImageView bandera;

        public MyViewHolder(View itemView) {
            super(itemView);
            year = itemView.findViewById(R.id.textViewYearGanadorRecycler);
            nombre = itemView.findViewById(R.id.textViewNombreGanadorRecycler);
            bandera = itemView.findViewById(R.id.imageViewBanderaRecycler);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .ganador_recycler_view_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ganador ganador = ganadores.get(position);
        StorageReference photoRef = banderasStorageReference.child(ganador.getNombreImagenBandera());
        Glide.with(context).load(photoRef).into(holder.bandera);
        holder.year.setText(Integer.valueOf(ganador.getYear()).toString());
        holder.nombre.setText(ganador.getNombre());
    }

    @Override
    public int getItemCount() {
        return ganadores.size();
    }
}