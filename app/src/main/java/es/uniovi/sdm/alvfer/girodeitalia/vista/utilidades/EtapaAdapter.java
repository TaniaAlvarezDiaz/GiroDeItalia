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

public class EtapaAdapter extends RecyclerView.Adapter<EtapaAdapter.MyViewHolder> {

    private List<Etapa> etapas;

    public EtapaAdapter(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView numero, tipo, kilometros;

        public MyViewHolder(View itemView) {
            super(itemView);
            numero = itemView.findViewById(R.id.textViewNumero);
            tipo = itemView.findViewById(R.id.textViewTipoEtapa);
            kilometros = itemView.findViewById(R.id.textViewKilometrosEtapa);
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
        holder.numero.setText(Integer.valueOf(etapa.getNumero()).toString());
        holder.tipo.setText(etapa.getTipo());
        holder.kilometros.setText(Integer.valueOf(etapa.getKilometros()).toString());
    }

    @Override
    public int getItemCount() {
        return etapas.size();
    }
}

