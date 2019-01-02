package es.uniovi.sdm.alvfer.girodeitalia.vista.utilidades;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Etapa;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Ganador;

public class GanadorAdapter extends RecyclerView.Adapter<GanadorAdapter.MyViewHolder> {

    private List<Ganador> ganadores;

    public GanadorAdapter(List<Ganador> ganadores) {
        this.ganadores = ganadores;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView year, nombre, bandera;

        public MyViewHolder(View itemView) {
            super(itemView);
            year = itemView.findViewById(R.id.textViewYear);
            nombre = itemView.findViewById(R.id.textViewNombre);
            bandera = itemView.findViewById(R.id.textViewBandera);
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
        holder.year.setText(Integer.valueOf(ganador.getYear()).toString());
        holder.nombre.setText(ganador.getNombre());
        holder.bandera.setText(ganador.getNombreImagenBandera());
    }

    @Override
    public int getItemCount() {
        return ganadores.size();
    }
}